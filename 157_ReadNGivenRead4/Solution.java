/*
    Read N Characters Given Read4

    The API: int read4(char *buf) reads 4 characters at a time from a file.

    The return value is the actual number of characters read.
    For example, it returns 3 if there is only 3 characters left in the file.

    By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

    Note:
    The read function will only be called once for each test case.

    Yu Fu, 09/29/2016
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    // Read until curIdx >= maxLimit or EOF.
    // EOF when read4 returns value less than 4.
    public int read(char[] buf, int n) {
        char[] read4Buffer = new char[4];
        boolean eof = false;
        int curIdx = 0;
        while (!eof) {
            int bytesRead = read4(read4Buffer);
            for (int i = 0; i < bytesRead; i++) {
                if (curIdx >= n) return n;
                buf[curIdx++] = read4Buffer[i];
            }
            if (bytesRead < 4) eof = true;
        }
        return curIdx;
    }
}