package com.conrradocamacho.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conrradocamacho.aluvery.R
import com.conrradocamacho.aluvery.ui.theme.Purple500
import com.conrradocamacho.aluvery.ui.theme.Teal200

@Preview(showBackground = true)
@Composable
private fun ChallengePreview() {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .widthIn(250.dp, 300.dp)
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(imageSize)
                    .background(brush = Brush.verticalGradient(colors = listOf(Purple500, Teal200)))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Image of product",
                    modifier = Modifier
                        .offset(x = imageSize / 2)
                        .size(imageSize)
                        .clip(shape = CircleShape)
                        .align(Alignment.Center)
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Teal200,
                                    Purple500
                                )
                            ),
                            shape = CircleShape
                        )
                )

            }
            Spacer(
                modifier = Modifier
                    .width(imageSize / 2)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = LoremIpsum(50).values.first(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )

            }
        }

    }
}