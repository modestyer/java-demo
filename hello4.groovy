import com.example.groovyDemo.dao.PersonDao
import com.example.groovyDemo.entity.Person
import com.example.groovyDemo.service.GroovyService
import com.example.groovyDemo.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service

@Component
class TestGroovy implements GroovyService{

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

