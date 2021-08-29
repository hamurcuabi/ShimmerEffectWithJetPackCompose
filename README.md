# ShimmerEffectWithJetPackCompose

Shimmer effect created with jetpack compose :partying_face:


<img src="https://user-images.githubusercontent.com/23655824/131250181-3036b36e-1917-48c7-84dd-c1c7f1b862bc.gif">

# How It Works :roll_eyes:

1. Lets create a list of color

	```val colors = listOf(
    Color.Gray.copy(alpha = 0.9f),
    Color.Gray.copy(alpha = 0.8f),
    Color.Gray.copy(alpha = 0.9f))
  
  2. We need a background. We will create it with *Brush*. This object has *start and end* offset. So we should animate it. Look at the picture and you will get the idea :monocle_face:

	val brush = linearGradient(
            colors = colors,
            start = Offset(
                currentOffset.value - calculatedOffSet,
                currentOffset.value - calculatedOffSet
            ),
            end = Offset(
                currentOffset.value,
                currentOffset.value
            ),
        )
  <img src="https://user-images.githubusercontent.com/23655824/131250524-3a933772-00ce-4aba-ac89-70574dffb085.jpg">
  
  3.Animation is here! This is the power of compose :sunglasses:
  
  	val currentOffset = animateFloatAsState(
            targetValue = if (animPlayed) calculatedMaxValue else 0f,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = animDuration,
                    delayMillis = animDelay,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        LaunchedEffect(key1 = true) {
            animPlayed = true
        }
      
      
  *Please fork this project and do not forget support star <a class="github-button" href="https://github.com/hamurcuabi/ShimmerEffectWithJetPackCompose" data-icon="octicon-star" aria-label="Star hamurcuabi/ShimmerEffectWithJetPackCompose on GitHub">:star:</a>:raised_hands:*
