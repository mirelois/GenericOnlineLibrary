package com.aa.coolreads.User.services;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.User.models.DefaultBookshelf;

import java.util.List;

public interface Sliceable {

    List<SliceInterfaceDTO> getSlices(DefaultBookshelf bookshelf, String isbn);

}
