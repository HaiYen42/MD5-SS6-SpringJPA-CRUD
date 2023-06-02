package rikkei.academy.repository;

import rikkei.academy.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
    //  @Transactional --> Đánh dấu transaction ở Repo để thao tác CSDL
    @Transactional
public class StudentRepositoryIMPL implements IStudentRepository{
        // PersistenceContext là tập các thể hiện của entity được quảnlý,tồn tại trong một kho dữ liệu
        // EntityManager em; --> Để thao tác các câu query
        @PersistenceContext
        EntityManager em;

    @Override
    public List<Student> findALl() {
        String qrFindAll = "SELECT st FROM Student  AS st";
        TypedQuery<Student> query = em.createQuery(qrFindAll, Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByName(String name) {
        // createQuery--> Truy vấn động
        String qrFindByName = "SELECT st FROM Student AS st WHERE st.name LIKE :name";
        TypedQuery<Student> query = em.createQuery(qrFindByName, Student.class);
        query.setParameter("name", name);
        return query.getResultList();
    }


    @Override
    public List<Student> findByNameStatic(String name) {
        // createNamedQuery--> Truy vấn tĩnh
        TypedQuery<Student> query = em.createNamedQuery("Student.FIND_BY_NAME", Student.class);
        query.setParameter("name","%" + name+ "%");
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        TypedQuery<Student> query = em.createNamedQuery("Student.FIND_BY_ID",Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Student student) {
        if (student.getId() !=null) {
            em.merge(student);
        } else{
            em.persist(student);
        }
    }

    @Override
    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }
}
