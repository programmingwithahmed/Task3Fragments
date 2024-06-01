package com.abc.task3fragments.utils

import android.content.Context
import android.content.res.AssetManager

class GeneralUtility {

    companion object {

        fun readTextFileFromAssets(context: Context, fileName: String): String {
            val assetManager: AssetManager = context.assets
            val inputStream = assetManager.open(fileName)
            val bufferedReader = inputStream.bufferedReader()
            val fileContent = bufferedReader.use { it.readText() }
            return fileContent
        }

    }

}