package com.emrehmrc.hmrceffect

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.emrehmrc.hmrceffect.ui.theme.HmrcEffectTheme

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkThemeEnable = remember {
                mutableStateOf(false)
            }
            val image = if (darkThemeEnable.value) {
                loadPicture(R.drawable.ic_night_mode).value
            } else {
                loadPicture(R.drawable.ic_light_mode).value
            }

            HmrcEffectTheme(
                darkTheme = darkThemeEnable.value
            ) {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        ListOfHmrcEffectItems()
                        FloatingCard(image, darkThemeEnable)
                    }
                }
            }
        }
    }

    @Composable
    fun FloatingCard(image: Bitmap?, darkThemeEnable: MutableState<Boolean>) {
        image?.let { img ->
            Card(
                modifier = Modifier
                    .padding(24.dp)
                    .defaultMinSize(48.dp),
                shape = RoundedCornerShape(48.dp),
                onClick = {
                    darkThemeEnable.value = !darkThemeEnable.value
                }, elevation = 8.dp
            ) {
                Image(
                    bitmap = img.asImageBitmap(),
                    "darkThemeEnable",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(MaterialTheme.colors.secondary)
                        .padding(8.dp)
                )
            }
        }
    }

    @Composable
    private fun ListOfHmrcEffectItems() {
        Column {
            LazyColumn {
                items(20) {
                    HmrcEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HmrcEffect(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(8.dp),
                            height = 45.dp,
                        )
                        HmrcEffect(
                            modifier = Modifier
                                .width(45.dp),
                            shape = RoundedCornerShape(45.dp),
                            height = 45.dp,
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun loadPicture(@DrawableRes defaultImage: Int = R.drawable.ic_launcher_background): MutableState<Bitmap?> {

        val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }
        // show default image while image loads
        Glide.with(LocalContext.current)
            .asBitmap()
            .load(defaultImage)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) = Unit
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    bitmapState.value = resource
                }
            })

        return bitmapState
    }
}
