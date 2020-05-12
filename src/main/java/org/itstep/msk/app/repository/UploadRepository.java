package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}
