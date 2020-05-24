package com.huiaong.pikachu.resources.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.resources.dto.PikaGoodsPicture;
import com.huiaong.pikachu.resources.dto.PikaPictureFile;

public interface PikaResourcesWriteService {

    Response<PikaGoodsPicture> upload(PikaPictureFile pictureFile);

}
