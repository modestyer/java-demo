import com.example.groovyDemo.dao.PersonDao
import com.example.groovyDemo.entity.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TestGroovy{

    @Autowired
    PersonDao personDao;

    Object insertTest(List<Person> list){
        int n = 3;
        for (Person person : list) {
            if(n==5){
                person=null;
            }
            person.setId(n);
            person.setVar1('测试');
            person.setPassWord('123456')
            System.out.println(person.getName());
            System.out.println(person.getId());
            System.out.println(person.getVar1());
            personService.insert(person)
            n++;
        }

    }

    @Override
    void execute(List<Person> list) {
        int n = 3;
        for (Person person : list) {
            if(n==5){
                person=null;
            }
            person.setId(n);
            person.setVar1('测试');
            person.setPassWord('123456')
            System.out.println(person.getName());
            System.out.println(person.getId());
            System.out.println(person.getVar1());
            personService.insert(person)
            n++;
        }
    }
}

