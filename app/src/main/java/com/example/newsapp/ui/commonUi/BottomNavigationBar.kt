package com.example.newsapp.ui.commonUi

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.newsapp.navigation.Destination

@Composable
fun BottomNavigationBar(selectedIndex:Int,markAsSelected:(Int, Destination)-> Unit) {
    NavigationBar {
        Destination::class.sealedSubclasses.filter { it.objectInstance!=null }.sortedBy { it.objectInstance!!.order }.map { it.objectInstance }.forEachIndexed { index,destination ->
            NavigationBarItem(
                selected =index==selectedIndex,
                onClick = {
                    markAsSelected.invoke(index,destination!!)
                          },
                icon = { Icon(destination!!.icon,"") },
                label = { Text(destination!!.label) }
            )        }
    }

}