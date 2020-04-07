/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.projectwork.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studied_words")
data class PolyglotData(
        @PrimaryKey(autoGenerate = true)
        val uniqueId: Long,

        @ColumnInfo(name = "language_id")
        val langId: Long = 0L,

        @ColumnInfo(name = "word_id")
        var wordId: Long = 0L,

        @ColumnInfo(name = "original_word")
        var originalWord: String = "not_studied_yet",

        @ColumnInfo(name = "is_studied")
        var isStudied: Boolean = false
)