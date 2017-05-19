package 正则表达式;
/*
 * 常用特殊符号
 * \d: 用于匹配0-9数字
 * \D:用于匹配非0-9数字
 * \w:用于匹配0-9，a-z A-Z 等字符
 * \W:用于匹配非0-9，a-z A-Z 等字符
 * ^: 是否以指定的表达式开头
 * $: 是否以指定的表达式结尾
 * *: 用于指定前面的表达式可以出现0次或者多次
 * +：  用于指定前面的表达式可以出现1次或者多次
 * ？：用于指定前面的表达式可以出现0次或者1次
 * |: 用于指定两项/多项之间的任意一个
 * {}:用于表达式可以出现的次数
 * .: 用于匹配"\n"以外的任意字符，如果需要使用小数点，需使用转义符
 * 
 * */
public class TestPeople {
	public static void main(String[] args) {
		//String regex="abc";
		//String user="abc";
		//定义验证邮箱的正则
		String regex="\\w*@\\w*\\.com";
		String user="ys123@163.com";
		boolean b=user.matches(regex);
		if(b){
			System.out.println("匹配");
		}
		else{
			System.out.println("不匹配");
		}
		//提取
		String s="西安215644邮电545646";
		//Pattern 和Matchaer 对象根据正则表达式进行提取所有电话号码
	}
}

