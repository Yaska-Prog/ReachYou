package com.example.reachyou.ui.screen.detailNews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.ui.component.BackButton
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun DetailNewsScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ){
        AsyncImage(
            model = "https://wallpaperaccess.com/full/685208.jpg",
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
        )
        BackButton()
        Box(modifier = modifier
            .padding(top = 250.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White),
            contentAlignment = Alignment.Center){
            Column(
                modifier = modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(modifier = modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(45.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = modifier.width(10.dp))
                    Text(text = "Christian Yaska Natawijaya", style = MaterialTheme.typography.headlineMedium)
                }
                Text(text = "Wallpaper Ciamik bagi kaum disabilitas", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
                Spacer(modifier = modifier.height(10.dp))

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris mollis orci sit amet gravida fermentum. Integer eu nisl metus. Cras vel dictum dolor, id aliquam urna. Donec faucibus nisl felis, non imperdiet dui hendrerit eu. Pellentesque posuere, neque sed gravida iaculis, eros eros efficitur purus, sit amet facilisis arcu sapien tincidunt turpis. Fusce sed aliquet enim, id luctus velit. Morbi fringilla lectus nisi, et molestie ligula tristique sit amet.\n" +
                            "Nullam vestibulum eros et tellus rhoncus pulvinar. Pellentesque lorem arcu, eleifend quis porta et, venenatis id lectus. Pellentesque mollis interdum quam, vulputate consectetur lectus maximus quis. Pellentesque ullamcorper tincidunt nunc, eu pretium nunc pulvinar eu. Sed ac ligula ultrices, varius elit nec, placerat nunc. Vivamus ornare a ligula eget tristique. Duis dapibus ante nec quam condimentum dictum. Donec eu volutpat ex.\n" +
                            "Maecenas dolor dolor, faucibus sed porta quis, aliquet porta velit. Sed euismod pulvinar lobortis. Curabitur vel fringilla felis. Duis auctor nisl vel pharetra convallis. Nam ex odio, viverra fringilla nisl vel, dignissim bibendum urna. Vivamus sed tempus leo. In felis lectus, commodo ut lorem id, pulvinar tempus justo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras fringilla nisi eu nibh consectetur scelerisque. Pellentesque consequat mattis ante a vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis sit amet varius est.\n" +
                            "Sed tempus ut lacus ac eleifend. Fusce pharetra enim velit, eu ullamcorper neque mollis et. In hac habitasse platea dictumst. Vivamus ante libero, cursus sit amet aliquam a, imperdiet sed turpis. Etiam pulvinar, dolor quis aliquam sodales, sem justo faucibus massa, eget pretium nunc leo vulputate libero. Suspendisse accumsan tristique purus, vel sodales nisi. Ut eget dui sem. Fusce ultricies nibh nunc, ac placerat libero vestibulum venenatis. Aenean leo ligula, convallis ut egestas eget, consectetur at ligula. Fusce sed nisl purus. Aliquam et ligula ut velit consectetur maximus quis ornare nisl. Proin laoreet ex eget nulla posuere finibus. Nulla pharetra sodales orci sit amet imperdiet. Phasellus convallis tempor bibendum.\n" +
                            "Mauris tempor, ligula id malesuada iaculis, est nibh dignissim ex, quis mollis arcu leo in dolor. Quisque ligula justo, iaculis vel magna vitae, auctor maximus velit. Sed est purus, placerat id nibh sit amet, maximus consectetur dui. Suspendisse potenti. In a blandit leo, sodales tempor nisi. Nunc ultrices, elit non iaculis pellentesque, nulla tellus gravida enim, id porttitor orci enim consectetur purus. Duis sed arcu quis ex dictum egestas eget a magna. "

                )
            }
        }
    }
}

@Preview
@Composable
fun DetailNewsPreview() {
    ReachYouTheme {
        DetailNewsScreen()
    }
}