import com.example.groovyDemo.entity.Person
import com.example.util.ExcelUtil
import org.springframework.transaction.annotation.Transactional

def hello(param1, param2) {
    return "hello groovy....................! the params is $param1 and $param2"
}



@Transactional(rollbackFor = Exception.class)
def importExcel(file,personService){
    List<Person> list = ExcelUtil.importExcel(file, 0, 1, Person.class);
    int n=3;
    for(Person person : list){
        /*if(n==3){
            person=null;
        }*/
        person.setId(n);
        person.setVar1('测试');
        person.setPassWord('123456')
        System.out.println(person.getName());
        System.out.println(person.getId());
        System.out.println(person.getVar1());
        personService.insert(person)
        n=n-1;
    }
}

