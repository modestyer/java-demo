import com.example.groovyDemo.service.PersonService
import net.dunotech.venus.system.entity.sys.SysGroovyInfo
import org.springframework.beans.factory.annotation.Autowired

def hello(param1, param2) {
    return "hello groovy....................! the params is $param1 and $param2"
}

@Transactional(rollbackFor = Exception.class)
def importExcel(file) {

    @Autowired
    PersonService personService;

    List<Person> list = ExcelUtil.importExcel(file, 0, 1, Person.class);
    int n = 3;
    for (Person person : list) {
        if(n==3){
        person=null;
    }
        person.setId(n);
        person.setVar1('测试');
        person.setPassWord('123456')
        System.out.println(person.getName());
        System.out.println(person.getId());
        System.out.println(person.getVar1());
        personService.insert(person)
        n = n - 1;
    }
}


def testGroovy(mapper) {
    SysGroovyInfo groovyInfo = mapper.selectById("1");
    System.out.println("groovy的执行结果是："+groovyInfo.getScriptName());
}

