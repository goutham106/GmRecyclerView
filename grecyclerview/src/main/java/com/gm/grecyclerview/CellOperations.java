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

import java.util.List;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
public interface CellOperations {

    void addCell(GmCell cell);

    void addCell(int atPosition, GmCell cell);

    void addCells(List<? extends GmCell> cells);

    void addCells(GmCell... cells);

    void addCells(int fromPosition, List<? extends GmCell> cells);

    void addCells(int fromPosition, GmCell... cells);

    <T extends GmCell & Updatable> void addOrUpdateCell(T cell);

    <T extends GmCell & Updatable> void addOrUpdateCells(List<T> cells);

    <T extends GmCell & Updatable> void addOrUpdateCells(T... cells);

    void removeCell(GmCell cell);

    void removeCell(int atPosition);

    void removeCells(int fromPosition, int toPosition);

    void removeCells(int fromPosition);

    void removeAllCells();

    void updateCell(int atPosition, Object payload);

    void updateCells(int fromPosition, int toPosition, List<Object> payloads);

    GmCell getCell(int atPosition);

    List<GmCell> getCells(int fromPosition, int toPosition);

    List<GmCell> getAllCells();

}

