package me.haliri.israj.appcore.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 11/21/17.
 */
@Service
public interface BaseRepository<T,D> {
    D getData();

    D getDataById(T id);

    D getDataByParameters(T parameters);

    void saveData(D parameters);

    void updateData(D parameters);

    void saveOrUpdate(D parameters);

    void deleteData(T id);
}
