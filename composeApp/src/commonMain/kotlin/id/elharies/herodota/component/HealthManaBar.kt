package id.elharies.herodota.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import id.elharies.herodota.theme.healthGreenColor
import id.elharies.herodota.theme.textBarWhiteColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HealthManaBar(
    modifier: Modifier = Modifier,
    background: Color = healthGreenColor,
    valueBar: Int = 100,
    valueRegen: Double = 2.0
) {
    Box(
        modifier = modifier.fillMaxWidth().height(30.dp).background(color = background),
        contentAlignment = Alignment.Center
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp, horizontal = 8.dp)
        ) {
            val (textValueBar, textValueRegen) = createRefs()

            Text(
                "$valueBar / $valueBar",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = textBarWhiteColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.constrainAs(textValueBar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text("+$valueRegen", style = MaterialTheme.typography.titleSmall.copy(
                color = textBarWhiteColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            ), modifier = Modifier.constrainAs(textValueRegen) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            })
        }
    }
}

@Preview()
@Composable
private fun PreviewHealthManaBar() {
    HealthManaBar()
}