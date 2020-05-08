package io.github.coderbuck.boring.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.coderbuck.boring.databinding.ActivityUiTestBinding

class TestUiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUiTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.item.apply {
            name.text = "podgorskiy / ALAE"
            description.text = "项目基于 Spring Boot 2.1.0 、 Jpa、 Spring Security、redis、Vue的前后端分离的后台管理系统，项目采用分模块开发方式， 权限控制采用 RBAC，支持数据字典与数据权限管理，支持一键生成前后端代码，支持动态路由"
            starCount.text = "123"
            lang.text = "Java"
        }
    }

}