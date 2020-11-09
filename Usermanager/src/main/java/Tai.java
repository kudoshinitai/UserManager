import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tai.org.usermanager.dto.UserDTO;

class Student{
	private String name;
	private int age;

	public Student() {
		
	}
	public String getName() {
	return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	
	//Hashcode randum so ngau nhien cho vung nho, override de vung nho giong nhau se randum giong nhau
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	
	//Mac dinh so sanh vung nho, override de so sanh gia tri
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}

public class Tai {
	public static void main(String[] args) throws JsonProcessingException {
//		Set <Student> students = new HashSet<>(2);
//		Set <Integer> ints =  new HashSet<>();
//		ints.add(1);
//		ints.add(1); //Can not add
//		Student a = new Student("Tai", 21);
//		Student b = new Student("Tai", 21);
//		students.add(a);
//		students.add(b);
//		System.out.println(new ObjectMapper().writeValueAsString(students));
//		System.out.println(ints);
		
		
		String jsonProperty = "FIRST_NAME";
		Field[] fields =  UserDTO.class.getDeclaredFields();
		for(Field field : fields) {
			if(field.isAnnotationPresent(JsonProperty.class)) {
				String annotationValue = field.getAnnotation(JsonProperty.class).value();
				if(StringUtils.equals(jsonProperty, annotationValue)) {
					System.out.println(field.getName());
					break;
				}
			}
		}
		
	}
}

