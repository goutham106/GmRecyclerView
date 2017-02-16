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

package com.gm.grecyclerview;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
class SimpleDiffCallbackDelegate extends DiffUtil.Callback {

  private List<SimpleCell> newList = new ArrayList<>();
  private List<SimpleCell> oldList = new ArrayList<>();

  public SimpleDiffCallbackDelegate(SimpleAdapter adapter, List<? extends SimpleCell> newCells) {
    this.oldList.addAll(adapter.getAllCells());
    this.newList.addAll(oldList);
    insertOrUpdateNewList(newCells);
    adapter.setCells(newList);
  }

  private void insertOrUpdateNewList(List<? extends SimpleCell> newCells) {
    for (SimpleCell newCell : newCells) {
      int index = indexOf(newList, newCell);
      if (index != -1) {
        newList.set(index, newCell);
      } else {
        newList.add(newCell);
      }
    }
  }

  private int indexOf(List<? extends SimpleCell> cells, SimpleCell cell) {
    for (SimpleCell c : cells) {
      if (c.getItemId() == cell.getItemId()) {
        return cells.indexOf(c);
      }
    }
    return -1;
  }

  @Override
  public int getOldListSize() {
    return oldList.size();
  }

  @Override
  public int getNewListSize() {
    return newList.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).getItemId() == newList.get(newItemPosition).getItemId();
  }

  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return ((Updatable) oldList.get(oldItemPosition)).areContentsTheSame(newList.get(newItemPosition).getItem());
  }

  public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    return ((Updatable) oldList.get(oldItemPosition)).getChangePayload(newList.get(newItemPosition).getItem());
  }

}
