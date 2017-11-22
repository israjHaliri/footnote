package me.haliri.israj.appcore.repository;

import me.haliri.israj.appcore.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by israjhaliri on 11/21/17.
 */
@Service
public interface UserRespository extends BaseRepository<Object,User> {}
