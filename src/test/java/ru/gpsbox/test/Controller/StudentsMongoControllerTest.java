package ru.gpsbox.test.Controller;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gpsbox.test.domain.mongo.Student;
import ru.gpsbox.test.MongoRestApplication;
import ru.gpsbox.test.service.KeySeqService;
import ru.gpsbox.test.service.Sampleservice;
import ru.gpsbox.test.service.StudentService;
import ru.gpsbox.test.repository.mongo.KeySeqRepo;
import ru.gpsbox.test.web.rest.StudentsMongoResource;
import ru.gpsbox.test.web.rest.Пиво;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = MongoRestApplication.class)
public class StudentsMongoControllerTest {

    private Student student = new Student("AABBCC", 1, "Vova", " Tractor");

    StudentsMongoResource studMongContr;
    @Mock
    private StudentService studentService;
    @Mock
    private KeySeqService seqRepo;

    @Before
    public void setUp() throws Exception {
        studMongContr = new StudentsMongoResource(this.studentService, this.seqRepo);
        studentService.insertMongoStudent(student);
    }

    @Test
    public void getStudentById() {
        //prepare
        when(studentService.findAllFromMongo()).thenReturn(ImmutableList.of());
        // nextSeq
        Collection<Student> list = studMongContr.getAllStudents();
        //validate
        verify(studentService).findAllFromMongo();
    }

    @Test
    public void getStudentByName() {
        //prepare
        when(studentService.findMongoStudentByName("Vova")).thenReturn(ImmutableList.of());
        //nextSeq
        Collection<Student> student = studMongContr.getStudentByName("Vova");
        //verify
        verify(studentService).findMongoStudentByName("Vova");
    }

    @Test
    public void deleteStudentById() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        studentService.insertMongoStudent(stud);
        verify(studentService).insertMongoStudent(stud);
        studMongContr.deleteStudentByKeySeq(2);
        verify(studentService).deleteMongoStudentByKeySeq(2);
    }

    @Test
    public void deleteStudentByName() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        studentService.insertMongoStudent(stud);
        verify(studentService).insertMongoStudent(stud);
        studMongContr.deleteStudentByName("Knik");
        verify(studentService).deleteMongoStudentByName("Knik");
    }

    @Test
    public void updateStudent() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        Student studUpd = new Student("BBCCDD", 2, "Prosp", " Velosipeds");
        studentService.saveMongoStudent(stud);

        Student studentByKeySeq = studentService.findOneMongoStudentByKeySeq(1);
        System.out.println(studentService.count());
//        assertThat(studentByKeySeq).isEqualTo(stud);
        assertThat(studentByKeySeq).isNotEqualTo("[]");
        studMongContr.updateStudent(2,studUpd);
//        verify(studentService).saveMongoStudent(studUpd);
    }

    @Test
    public void testLambdaAndStreams() {
        Collection<Integer> number
                = IntStream.range(0, 10).boxed().collect(toSet());
        number.parallelStream().forEach(System.out::println);
        number.forEach(System.out::println);

        Function<String, String> mapper1 = String::toUpperCase; //method reference
        Function<String, Integer> mapp2 = Integer::new; //method reference
        Function<String, String> mapper2 = x -> x.toUpperCase(); // Lambda
        Function<String, Integer> mapp1 = x -> new Integer(x); //Lambda
        int reduce = IntStream.rangeClosed(1, 30).parallel().filter(e -> e > 4).reduce(0, (sum, elt) -> sum + elt);
        int[] squer = IntStream.rangeClosed(1, 10).parallel().map(x -> x * x).toArray();
        System.out.println(mapper1.apply("Vasiliy Petrovich"));
        System.out.println(mapper2.apply("Vasiliy Petrovich"));
        System.out.println(mapp1.apply("22"));
        System.out.println(mapp2.apply("22"));
        System.out.println(reduce);
        System.out.println(squer);
        System.out.println(isPrime(2));
        System.out.println(isPrime(3));
        System.out.println(isPrime(4));
        System.out.println(isPrime(5));
        System.out.println(isPrime(6));

        List<Integer> list = Arrays.asList(1, 2, 3, 7, 10, 9, 12);
        Predicate<Integer> isGreaterThen3 = num -> num > 3;
        Function<Integer, Predicate<Integer>> isGreater = pivot -> chislo
                -> chislo > pivot;
        // TODO Higher order functions, Make it works and make it better real soon
        //todo Lazy and composition , Transformation objects over Mutation
        System.out.println(list.stream()
                .filter(isGreater.apply(2))
                .filter(isGreaterThen3)
                .filter(val -> val % 2 == 0)
                .map(val -> val * 2)
                .findFirst()
                .get());
        // todo More declarative approach method reference
        System.out.println(list.stream()
                .filter(Sampleservice::isGreaterThen3)
                .filter(Sampleservice::isEven)
                .map(Sampleservice::doubleIt)
                .findFirst()
                .get());

        //todo imperative method
        int totalVals = new Sampleservice().totalValues(list, e -> true);
        int totalEvens = new Sampleservice().totalValues(list, e -> e % 2 == 0);

        System.out.println("Total values is: " + totalVals);
        System.out.println("Total for Even: " + totalEvens);

        int totalLambdaVals = new Sampleservice().totalValuesLambda(list, e -> true);
        int totalLambdaEvens = new Sampleservice().totalValuesLambda(list, e -> e % 2 == 0);

        System.out.println("Total values Lambda is: " + totalLambdaVals);
        System.out.println("Total for Lambda Even: " + totalLambdaEvens);

        // TODO Java test UTF8
        Пиво пиво = new Пиво();
        пиво.студентыПьютПиво();

        System.out.println(
                Stream.of(
                        -5,1,2,3,4,5,-7,-9,-10
                ).max(Math::max).get()
        );
    }


    private boolean isPrime(int i) {
        return i > 1 && IntStream.range(2, i).noneMatch(index -> i % index == 0);
    }
}