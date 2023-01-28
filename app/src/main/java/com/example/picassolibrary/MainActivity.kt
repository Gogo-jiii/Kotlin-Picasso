package com.example.picassolibrary

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.picassolibrary.databinding.ActivityMainBinding
import com.squareup.picasso.*
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLoadImage.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            Picasso
                .get()
                .load(
                    "https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                            "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                            "=format&fit=crop&w=750&q=80"
                )
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.baseline_account_circle)
                .error(R.drawable.baseline_error)
                .tag("tag")
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_STORE)
                .noFade()
                .into(binding.imageView, object : Callback {
                    override fun onSuccess() {
                        binding.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.progressBar.visibility = View.GONE
                    }
                })
        }

        binding.btnLoadImageAsBitmap.setOnClickListener {
            val target: Target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                    binding.imageView.setImageBitmap(bitmap)
                }

                override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                    binding.imageView.setImageDrawable(errorDrawable)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    binding.imageView.setImageDrawable(placeHolderDrawable)
                }
            }
            Picasso
                .get()
                .load(
                    "https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                            "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                            "=format&fit=crop&w=750&q=80"
                )
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.baseline_account_circle)
                .error(R.drawable.baseline_error)
                .tag("tag")
                .into(target)
        }

        binding.btnClearImageview.setOnClickListener {
            binding.imageView.setImageDrawable(
                null
            )
        }
    }
}