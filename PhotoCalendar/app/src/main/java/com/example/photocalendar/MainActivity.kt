package com.example.photocalendar

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.photocalendar.databinding.ActivityMainBinding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //갤러리 요청 런처
        val requestGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            try{
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio
                //이미지 로딩
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let{
                    binding.ivImage.setImageBitmap(bitmap)
                }?:let{
                    Log.d("TAG", "onCreate: bitmap null")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        binding.btnOpenGallley.setOnClickListener {
            //갤러리 앱
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        //카메라 요청 런처
        var filePath=""
        val requestCameraFileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //카메라 앱
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath,option)
            bitmap?.let{
                binding.ivImage.setImageBitmap(bitmap)
            }
        }
        binding.btnOpenCamera.setOnClickListener {
            //카메라 앱
            //파일 준비
            val timeStamp:String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir:File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI:Uri = FileProvider.getUriForFile(
                this, "com.example.photocalendar.fileprovider",file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            requestCameraFileLauncher.launch(intent)
        }

    }

    private fun calculateInSampleSize(fileUri:Uri, reqWidth:Int, reqHeight:Int):Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try{
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        }catch (e:Exception){
            e.printStackTrace()
        }
        val(height:Int, width:Int) = options.run{outHeight to outWidth}
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth){
            val halfHeight: Int = height /2
            val halfWidth : Int = width/2
            while (halfHeight/inSampleSize >= reqHeight&&
                    halfWidth/inSampleSize >=reqWidth){
                inSampleSize*=2
            }
        }
        return inSampleSize
    }
}