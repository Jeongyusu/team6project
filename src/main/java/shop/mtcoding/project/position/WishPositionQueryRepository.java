package shop.mtcoding.project.position;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.mtcoding.project.skill.SkillRequest.MySkill;

@Repository
public class WishPositionQueryRepository {

    @Autowired
    private EntityManager em;

}
