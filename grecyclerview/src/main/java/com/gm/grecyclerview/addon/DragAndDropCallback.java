/*
 * Copyright (c) 2017 Gowtham Parimalazhagan
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

package com.gm.grecyclerview.addon;

import android.view.View;

import com.gm.grecyclerview.GmRecyclerView;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
public abstract class DragAndDropCallback<T> {

    public boolean enableDefaultRaiseEffect() {
        return true;
    }

    public void onCellDragStarted(GmRecyclerView GmRecyclerView, View itemView, T item, int position) {
    }

    public void onCellMoved(GmRecyclerView GmRecyclerView, View itemView, T item, int fromPosition, int toPosition) {
    }

    public void onCellDropped(GmRecyclerView GmRecyclerView, View itemView, T item, int initialPosition, int toPosition) {
    }

    public void onCellDragCancelled(GmRecyclerView GmRecyclerView, View itemView, T item, int currentPosition) {
    }

}

