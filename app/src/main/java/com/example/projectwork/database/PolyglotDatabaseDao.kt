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

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface PolyglotDatabaseDao {

    @Insert
    fun insert(word: PolyglotData)

    @Update
    fun update(word: PolyglotData)

    @androidx.room.Query("SELECT * FROM studied_words WHERE language_id = :langKey AND word_id = :wordKey")
    fun getWord(langKey: Long, wordKey: Long): PolyglotData?

    @androidx.room.Query("SELECT * FROM studied_words WHERE language_id = :langKey AND is_studied = 1 ORDER BY word_id ASC LIMIT 1")
    fun getFirstWord(langKey: Long): PolyglotData?

    @androidx.room.Query("SELECT * FROM studied_words WHERE language_id = :langKey")
    fun getWords(langKey: Long): LiveData<List<PolyglotData>>

    @androidx.room.Query("SELECT * FROM studied_words WHERE language_id = :langKey AND is_studied = 1")
    fun getStudiedWords(langKey: Long): LiveData<List<PolyglotData>>

    @androidx.room.Query("SELECT COUNT(DISTINCT language_id) FROM studied_words")
    fun getLangCount(): Int
}
