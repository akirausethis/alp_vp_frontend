package com.mdanielelel.danielfrontend.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mdanielelel.danielfrontend.R
import com.mdanielelel.danielfrontend.ui.components.SvgIconComposable

@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<BottomNavItem>) {
    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        // First half of items
        items.take(2).forEach { item ->
            NavigationBarItem(
                icon = {
                    SvgIconComposable(
                        resourceId = item.iconResId,
                        color = if (currentDestination?.hierarchy?.any { it.route == item.route } == true)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                label = { Text(item.label) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(id = navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        // Center Add button
        NavigationBarItem(
            icon = {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .width(56.dp)
                        .background(Color(0xFFFF5722), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    SvgIconComposable(
                        resourceId = R.raw.ic_add,
                        color = Color.White
                    )
                }
            },
            selected = false,
            onClick = { /* Handle add click */ }
        )

        // Second half of items
        items.takeLast(2).forEach { item ->
            NavigationBarItem(
                icon = {
                    SvgIconComposable(
                        resourceId = item.iconResId,
                        color = if (currentDestination?.hierarchy?.any { it.route == item.route } == true)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                label = { Text(item.label) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(id = navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}