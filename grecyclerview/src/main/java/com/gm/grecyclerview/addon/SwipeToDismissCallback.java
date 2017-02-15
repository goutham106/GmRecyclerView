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

import android.graphics.Canvas;
import android.view.View;

import com.gm.grecyclerview.GmRecyclerView;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
public abstract class SwipeToDismissCallback<T> {

  public boolean enableDefaultFadeOutEffect() {
    return true;
  }

  public void onCellSwiping(GmRecyclerView GmRecyclerView, View itemView,  T item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
  }

  public void onCellSettled(GmRecyclerView GmRecyclerView, View itemView, T item, int position) {
  }

  public void onCellDismissed(GmRecyclerView GmRecyclerView, T item, int position) {
  }

}
