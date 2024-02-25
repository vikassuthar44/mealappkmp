package screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomButton
import widget.CustomTextView

data class OnBoardingData(
    val title: String,
    val subTitle: String,
    val imageUrl: String
)

// ACTUAL OFFSET
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

// OFFSET ONLY FROM THE LEFT
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

// OFFSET ONLY FROM THE RIGHT
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreenUI(
    onSkip: () -> Unit,
    onCompleted: () -> Unit
) {
    val list = listOf(
        OnBoardingData(
            title = "Engaging Superb Home Makers Around",
            subTitle = "It's a platform for home makers where they\ncan show their cooking talent and earn\nrevenue",
            imageUrl = "first_img.xml"
        ),
        OnBoardingData(
            title = "Foodies Food With Great Express Delivery",
            subTitle = "Fast and easy food delivery from\nthe best restaurant near you",
            imageUrl = "second_img.xml"
        ),
        OnBoardingData(
            title = "Schedule Order to never Miss a Meal",
            subTitle = "You can choose your schedule\norder if you don't wanna miss any\nMeal On Time.",
            imageUrl = "third_img.xml"
        )
    )
    var elements by remember {
        mutableStateOf(list)
    }
    var currentIndex by remember {
        mutableStateOf(0)
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        list.size
    }

    val coroutine = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HorizontalPager(
                state = pagerState
            ) { pageIndex ->
                AnimatedVisibility(
                    visible = true,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally()
                ) {
                    SingleBoardingFirstView(
                        onBoardingData = elements[pageIndex],
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CustomButton(
                    buttonTitle = "Continue",
                    buttonTextColor = Color.White,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    modifier1 = Modifier
                        .fillMaxWidth()
                ) {
                    if (currentIndex == 2) {
                        onCompleted.invoke()
                    } else {
                        elements = list
                        currentIndex++
                        coroutine.launch {
                            pagerState.animateScrollToPage(
                                page = currentIndex
                            )
                        }
                    }
                }
                /*Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(size = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        if (currentIndex == 2) {
                            onCompleted.invoke()
                        } else {
                            elements = list
                            currentIndex++
                            coroutine.launch {
                                pagerState.animateScrollToPage(
                                    page = currentIndex
                                )
                            }
                        }
                    }
                ) {
                    CustomTextView(
                        text = "Continue",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White
                        )
                    )
                }*/
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextView(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 20.dp))
                        .clickable {
                            onSkip.invoke()
                        }.padding(horizontal = 50.dp, vertical = 10.dp),
                    text = "Skip",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SingleBoardingFirstView(onBoardingData: OnBoardingData) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 20.dp).fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(onBoardingData.imageUrl),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextView(
            modifier = Modifier,
            text = onBoardingData.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.W900
            )
        )
        CustomTextView(
            modifier = Modifier,
            text = onBoardingData.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }

}