package shop.mtcoding.project.position;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    public List<Position> 포지션이름() {
        List<Position> positionList = positionRepository.findAll();
        return positionList;
    }

}