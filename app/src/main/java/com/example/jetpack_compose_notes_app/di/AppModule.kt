package com.example.jetpack_compose_notes_app.di

import android.app.Application
import androidx.room.Room
import com.example.jetpack_compose_notes_app.feature_note.data.data_source.NoteDatabase
import com.example.jetpack_compose_notes_app.feature_note.data.data_source.NoteDatabase.Companion.DATABASE_NAME
import com.example.jetpack_compose_notes_app.feature_note.data.repository.NoteRepositoryImpl
import com.example.jetpack_compose_notes_app.feature_note.domain.repository.NoteRepository
import com.example.jetpack_compose_notes_app.feature_note.domain.use_case.AddNote
import com.example.jetpack_compose_notes_app.feature_note.domain.use_case.DeleteNote
import com.example.jetpack_compose_notes_app.feature_note.domain.use_case.GetNote
import com.example.jetpack_compose_notes_app.feature_note.domain.use_case.GetNotes
import com.example.jetpack_compose_notes_app.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providerNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providerNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}