package com.example.jetpack_compose_notes_app.feature_note.domain.use_case

import com.example.jetpack_compose_notes_app.feature_note.domain.model.InvalidNoteException
import com.example.jetpack_compose_notes_app.feature_note.domain.model.Note
import com.example.jetpack_compose_notes_app.feature_note.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title on note cant be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content on note cant be empty.")
        }
        noteRepository.insertNote(note)
    }
}