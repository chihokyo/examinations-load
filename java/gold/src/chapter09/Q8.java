package chapter09;

public class Q8 {
    public static void main(String[] args) {
        try (SampleResource sampleResource = new SampleResource();) {
            // 这里发生错误了之后按照close → catch → finally的顺序
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }
}

class SampleResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("close");
    }
}