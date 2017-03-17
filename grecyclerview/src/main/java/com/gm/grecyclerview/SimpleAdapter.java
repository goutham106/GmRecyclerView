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

import android.support.v4.view.MotionEventCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gm.grecyclerview.behavior.DragAndDropHelper;
import com.gm.grecyclerview.behavior.OnItemDismissListener;
import com.gm.grecyclerview.behavior.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder>
  implements OnItemMoveListener, OnItemDismissListener, CellOperations {

  private List<SimpleCell> cells;
  private SparseArray<SimpleCell> cellTypeMap;
  private DragAndDropHelper dragAndDropHelper;

  SimpleAdapter() {
    this.cells = new ArrayList<>();
    this.cellTypeMap = new SparseArray<>();
    setHasStableIds(true);
  }

  void setDragAndDropHelper(DragAndDropHelper dragAndDropHelper) {
    this.dragAndDropHelper = dragAndDropHelper;
  }

  @Override
  public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    SimpleCell cell = cellTypeMap.get(viewType);
    View view = LayoutInflater.from(parent.getContext()).inflate(cell.getLayoutRes(), parent, false);
    final SimpleViewHolder viewHolder = cell.onCreateViewHolder(parent, view);

    if (dragAndDropHelper != null && dragAndDropHelper.getDragHandleId() != 0) {
      View dragHandle = viewHolder.itemView.findViewById(dragAndDropHelper.getDragHandleId());
      dragHandle.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
            dragAndDropHelper.onStartDrag(viewHolder);
          }
          return false;
        }
      });
    }

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final SimpleViewHolder holder, int position, List<Object> payloads) {
    final SimpleCell cell = cells.get(position);

    holder.bind(cell);

    if (cell.getOnCellClickListener() != null) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          cell.getOnCellClickListener().onCellClicked(cell.getItem());
        }
      });
    }

    if (cell.getOnCellLongClickListener() != null) {
      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          cell.getOnCellLongClickListener().onCellLongClicked(cell.getItem());
          return true;
        }
      });
    }

    if (cell.getOnCellClickListener2() != null) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          cell.getOnCellClickListener2().onCellClicked(cell, holder, cell.getItem());
        }
      });
    }

    if (cell.getOnCellLongClickListener2() != null) {
      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          cell.getOnCellLongClickListener2().onCellLongClicked(cell, holder, cell.getItem());
          return true;
        }
      });
    }

    Object payload = null;
    if (payloads != null && payloads.size() > 0) {
      payload = payloads.get(0);
    }

    cell.onBindViewHolder(holder, position, holder.itemView.getContext(), payload);
  }

  @Override
  public void onBindViewHolder(SimpleViewHolder holder, int position) {
    onBindViewHolder(holder, position, null);
  }

  @Override
  public void onViewRecycled(SimpleViewHolder holder) {
    holder.getCell().onUnbindViewHolder(holder);
    holder.unbind();
  }

  @Override
  public int getItemCount() {
    return cells.size();
  }

  @Override
  public int getItemViewType(int position) {
    return cells.get(position).getLayoutRes();
  }

  @Override
  public long getItemId(int position) {
    return cells.get(position).getItemId();
  }

  private void addCellType(SimpleCell cell) {
    if (!isCellTypeAdded(cell)) {
      cellTypeMap.put(cell.getLayoutRes(), cell);
    }
  }

  private void addCellTypes(List<SimpleCell> cells) {
    for (SimpleCell cell : cells) {
      addCellType(cell);
    }
  }

  private void removeCellType(SimpleCell cell) {
    boolean hasCellType = false;

    for (SimpleCell simpleCell : cells) {
      if (simpleCell.getClass().equals(cell.getClass())) {
        hasCellType = true;
      }
    }

    if (isCellTypeAdded(cell) && !hasCellType) {
      cellTypeMap.remove(cell.getLayoutRes());
    }
  }

  private boolean isCellTypeAdded(SimpleCell cell) {
    return cellTypeMap.indexOfKey(cell.getLayoutRes()) >= 0;
  }

  @Override
  public void addCell(SimpleCell cell) {
    addCell(cells.size(), cell);
  }

  @Override
  public void addCell(int atPosition, SimpleCell cell) {
    cells.add(atPosition, cell);
    addCellType(cell);
    notifyItemInserted(atPosition);
  }

  @Override
  public void addCells(List<? extends SimpleCell> cells) {
    if (cells.isEmpty()) {
      notifyDataSetChanged();
      return;
    }

    int initialSize = this.cells.size();
    for (SimpleCell cell : cells) {
      this.cells.add(cell);
      addCellType(cell);
    }

    notifyItemRangeInserted(initialSize, cells.size());
  }

  @Override
  public void addCells(SimpleCell... cells) {
    addCells(Arrays.asList(cells));
  }

  @Override
  public void addCells(int fromPosition, List<? extends SimpleCell> cells) {
    if (cells.isEmpty()) {
      notifyDataSetChanged();
      return;
    }

    int pos = fromPosition;
    for (SimpleCell cell : cells) {
      this.cells.add(pos++, cell);
      addCellType(cell);
    }

    notifyItemRangeInserted(fromPosition, cells.size());
  }

  @Override
  public void addCells(int fromPosition, SimpleCell... cells) {
    addCells(fromPosition, Arrays.asList(cells));
  }

  @Override
  public <T extends SimpleCell & Updatable> void addOrUpdateCell(T cell) {
    addOrUpdateCells(Collections.singletonList(cell));
  }

  @Override
  public <T extends SimpleCell & Updatable> void addOrUpdateCells(List<T> cells) {
    SimpleDiffCallbackDelegate callbackDelegate = new SimpleDiffCallbackDelegate(this, cells);
    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callbackDelegate);
    diffResult.dispatchUpdatesTo(this);
  }

  @Override
  public <T extends SimpleCell & Updatable> void addOrUpdateCells(T... cells) {
    addOrUpdateCells(Arrays.asList(cells));
  }

  @Override
  public void removeCell(SimpleCell cell) {
    int position = cells.indexOf(cell);
    cells.remove(cell);
    removeCellType(cell);
    notifyItemRemoved(position);
  }

  @Override
  public void removeCell(int atPosition) {
    removeCell(cells.get(atPosition));
  }

  @Override
  public void removeCells(int fromPosition, int toPosition) {
    for (int i = fromPosition; i <= toPosition; i++) {
      SimpleCell cell = cells.get(i);
      cells.remove(cell);
      removeCellType(cell);
    }
    notifyItemRangeRemoved(fromPosition, toPosition - fromPosition + 1);
  }

  @Override
  public void removeCells(int fromPosition) {
    removeCells(fromPosition, cells.size() - 1);
  }

  @Override
  public void removeAllCells() {
    cells.clear();
    cellTypeMap.clear();
    notifyDataSetChanged();
  }

  @Override
  public void updateCell(int atPosition, Object payloads) {
    notifyItemChanged(atPosition, payloads);
  }

  @Override
  public void updateCells(int fromPosition, int toPosition, List<Object> payloads) {
    notifyItemRangeChanged(fromPosition, toPosition - fromPosition + 1, payloads);
  }

  @Override
  public SimpleCell getCell(int atPosition) {
    return cells.get(atPosition);
  }

  @Override
  public List<SimpleCell> getCells(int fromPosition, int toPosition) {
    return cells.subList(fromPosition, toPosition + 1);
  }

  @Override
  public List<SimpleCell> getAllCells() {
    return cells;
  }

  public boolean isEmpty() {
    return cells.isEmpty();
  }

  public int getCellCount() {
    return cells.size();
  }

  void setCells(List<? extends SimpleCell> cells) {
    this.cells.clear();
    this.cells.addAll(cells);
    addCellTypes(this.cells);
  }

  @Override
  public void onItemMoved(int fromPosition, int toPosition) {
    Collections.swap(cells, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
  }

  @Override
  public void onItemDismissed(int position) {
    cells.remove(position);
    notifyItemRemoved(position);
  }

}