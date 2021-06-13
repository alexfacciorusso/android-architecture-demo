package com.alexfacciorusso.architecturedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import com.alexfacciorusso.architecturedemo.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentFactory: FragmentFactory

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}