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

package com.gm.grecyclerview.behavior;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class DragAndDropHelper extends ItemTouchHelper
  implements OnStartDragListener {

  private int dragHandleId;

  private DragAndDropHelper(Callback callback) {
    super(callback);
  }

  public static DragAndDropHelper create(OnItemMoveListener callback,
                                         DragAndDropOptions options) {
    DragAndDropItemCallback simpleCallback = new DragAndDropItemCallback(callback, options);
    DragAndDropHelper helper = new DragAndDropHelper(simpleCallback);
    helper.dragHandleId = options.getDragHandleId();
    return helper;
  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    startDrag(viewHolder);
  }

  public int getDragHandleId() {
    return dragHandleId;
  }

}
