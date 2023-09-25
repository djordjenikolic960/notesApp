package com.example.jetpack_compose_notes_app.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_compose_notes_app.feature_note.presentation.add_edit_note.components.AddEditNoteScreen
import com.example.jetpack_compose_notes_app.feature_note.presentation.notes.components.NotesScreen
import com.example.jetpack_compose_notes_app.feature_note.presentation.util.Screen
import com.example.jetpack_compose_notes_app.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                }
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesScreen.route
                ) {
                    composable(
                        route = Screen.NotesScreen.route
                    ) {
                        NotesScreen(navController = navController)
                    }

                    composable(
                        route = Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "noteColor"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(navController = navController, noteColor = color)
                    }
                }

            }
        }
    }
}