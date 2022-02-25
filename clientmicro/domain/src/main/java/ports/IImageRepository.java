package ports;

import model.Image;

import java.util.List;
import java.util.Optional;

public interface IImageRepository {
    Optional<Image> findFirstByIdTypeAndIdNumber(String idType, Long idNumber);
    List<Image> findAll();
    List<Image> findAllByIdNumberIn(List<Long> idNumbers);
}
