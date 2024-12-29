package id.elharies.herodota.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import hero_dota.composeapp.generated.resources.Res
import hero_dota.composeapp.generated.resources.ic_attribute_agility
import hero_dota.composeapp.generated.resources.ic_attribute_intelligence
import hero_dota.composeapp.generated.resources.ic_attribute_strength
import org.jetbrains.compose.resources.painterResource

@Composable
fun String.getImageAttribute(): Painter {
    return when (this) {
        "agi", "Agility" -> painterResource(Res.drawable.ic_attribute_agility)
        "str", "Strength" -> painterResource(Res.drawable.ic_attribute_strength)
        else -> painterResource(Res.drawable.ic_attribute_intelligence)
    }
}