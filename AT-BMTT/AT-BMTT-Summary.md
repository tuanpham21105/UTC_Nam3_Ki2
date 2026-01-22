# AT-BMTT

## Chương 1: Tổng quan

### 1. Giới thiệu

- An toàn và Bảo mật thông tin là Mật mã học.

- Mật mã học là lĩnh vực khoa học nghiên cứu các phương pháp, kỹ thuật đảm bảo an toàn và bảo mật trong truyền tin liên lạc. Biến đổi bản gốc thành 1 dạng bí mật.

- Mật mã học là kết hợp của 2 lĩnh vực:
  
  - Mã học: nghiên cứu các kỹ thuật toán học nhằm cung cấp công cụ, dịch vụ đảm bảo an toàn thông tin.
  
  - Thám mã: nghiên cứu các kỹ thuật toán học phục vụ phân tích phá mật mã hoặc tạo ra đoạn mã giả để đánh lừa bên nhận tin.

- Kỹ thuật mã hóa cơ bản:
  
  - Thay thế: kí tự, nhóm kí tự gốc bị thay thế bởi kí tự, nhóm kí tự khác.
  
  - Hoán vị: các từ bản rõ được xắp sếp lại theo 1 phương thức nhất định.

### 2. Một số khái niệm cơ bản

- Yêu cầu của AT&BM hệ thống thông tin:
  
  - Tính bảo mật (Confidentiality): bảo vệ dữ liệu ko bị lộ ra ngoài 1 cách trái phép.
  
  - Tính toàn vẹn (Integrity): chỉ những người dùng được ủy quyền mới được phép chỉnh sửa dữ liệu.
  
  - Tính sẵn sàng (Availability): đảm bảo dữ liệu luôn sẵn sàng khi người dùng hoặc  ứng dụng được ủy quyền yêu cầu.
  
  - Tính chống từ chối (Non-repudiation): ngăn chặn từ chối một hành vi đã làm.

- Mục tiêu của AT&BM hệ thống thông tin:
  
  - Ngăn chặn: Ngăn chặn kẻ tấn công vi phạm các chính sách bảo mật.
  
  - Phát hiện: Phát hiện các vi phạm chính sách bảo mật.
  
  - Phục hồi: Chặn hành vi đang diễn ra, đánh giá và sửa lỗi. Tiếp tục hoạt động ngay cả khi tấn công đã xảy ra.

### 3. Các bước cơ bản trong ATBMTT

#### Bước 1: Xác định các mối đe dọa

- Các mối đe dọa là những sự kiện ảnh hưởng đến an toàn của hệ thống thông tin.

- Có 4 loại mối đe dọa:
  
  - Xem thông tin 1 cách bất hợp pháp
  
  - Chỉnh sửa thông tin 1 cách bất hợp pháp
  
  - Từ chối dịch vụ
  
  - Từ chối hành vi

- Một số mối đe dọa thường gặp:
  
  - Lỗi và thiếu sót của người dùng (Errors and Omissions)
  
  - Gian lận và đánh cắp thông tin (Fraud and Theft)
  
  - Kẻ tấn công nguy hiểm (Malicious Hackers)
  
  - Mã nguy hiểm (Malicious Code)
  
  - Tấn công từ chối dịch vụ (Denial-of-Service Attacks)
  
  - Social Engineering

#### Bước 2: Lựa chọn chính sách bảo mật

- Việc bảo mật hệ thống cần có một chính sách bảo mật rõ ràng.

- Xây dựng và lựa chọn các chính sách bảo mật cho hệ thống phải dựa theo các chính
  sách bảo mật do các tổ chức uy tín về bảo mật định ra (compliance).

- Cần có những chính sách bảo mật riêng cho những yêu cầu bảo mật khác nhau.

- Chính sách bảo mật cần cân bằng giữa 3 yếu tố:
  
  - Độ bảo mật
  
  - Chi phí
  
  - Hiệu suất

#### Bước 3: Lựa chọn cơ chế bảo mật

- Xác định cơ chế bảo mật phù hợp để hiện thực các chính sách bảo mật và đạt được các mục tiêu bảo mật đề ra.

- Có 4 cơ chế bảo mật:
  
  - Điều khiển truy cập (Access control): cơ chế điều khiển, quản lý các truy cập vào hệ thống CSDL.
  
  - Điểu khiển suy luận (Inference control): quản lý, điều khiển các truy cập vào những CSDL thống kê, bởi vì từ những dữ liệu thống kê có thể suy luận ra được thông tin nhạy cảm.
  
  - Điều khiển dòng thông tin: dòng thông tin là khi dữ kiệu được đọc từ A và ghi vào B, điều khiển dòng thông tin nhằm ngăn thông tin đi từ đối tượng dữ liệu đc bảo vệ sang đối tượng dữ liệu ít được bảo vệ hơn.
  
  - Mã hóa (Encryption): là những thuật toán nằm chuyển đổi văn bản gốc sang văn bản mã hóa, mã hóa được dùng để bảo vệ dữ liệu nhảy cảm.

## Chương 2: Hệ mã cổ điển

### 1. Mã cổ điển

- Mã cổ điển (Cryptography) có mục tiêu cơ bản nhất là gửi 1 tin nhắn mà ko ai ngoài người nhận đọc đc.

- Một hệ mã được coi là an toàn nếu thuật toán tốt nhất để giải mã cần N phép toán, với N rất lớn.

- Phân loại hệ mật mã có 3 cách:
  
  - Xác định thao tác mã hóa dùng trên bản rõ:
    
    - Phép thế: thay thế kí tự trên bản rõ bằng ký tự khác ký tự trên bản mã.
    
    - Hoán vị: thay đổi vị trí các kí tự trên bản rõ.
    
    - Tích: kết hợp 2 cách trên, thay thế và hóan vị kí tự bản rõ.
  
  - Xác định số khóa đc sdung trong mã hóa và giải mã:
    
    - Đối xứng: 1 khóa duy nhất đc dùng. (khóa bí mật)
    
    - Ko đối xứng: 2 khóa khác nhau. (khóa công khai)
  
  - Xác định cách bản rõ đc xử lý:
    
    - Khối: dữ liệu đc chia thành nhiều khối và áp dụng thuật toán mã với tham số khóa cho từng khối.
    
    - Dòng: từng đơn vị thông tin đầu vào (bit hoặc byte) đc xử lý liên tục tạo ra phần từ đầu ra tương ứng.

#### Mã dịch chuyển

- Mã dịch chuyển: Mã hóa bằng cách là thay mỗi chữ trong bản rõ bằng chữ thứ k tiếp theo trong bảng chữ cái.
  
  - Gán số thứ tự cho mỗi chứ trong bảng chữ cái.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-17-15-51-image.png" title="" alt="" data-align="center">

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-17-16-24-image.png" title="" alt="" data-align="center">

- Ví dụ điển hình là Mã Caesar, khi k bằng 3.

- Ví dụ:
  
  - Bản rõ: Hello
  
  - k là khóa bằng 3
  
  - Bản mã: Khoor

- Có thể giải mà bằng Vét cạn (Brute-force): thử tất cả các giá trị của k ∈ {0, 1, 2, . . . , 25} → 26 trường hợp. => Dễ bị giải mã.

#### Mã bảng mã chữ đơn

- Mã bảng mã chữ đơn: Khắc phục cho tính dễ giải mã của Mã dịch chuyển bằng cách tạo ra các bước nhảy khác nhau cho các chữ, mỗi kĩ tự trên bản rõ sẽ có một k để dịch chuyển khác nhau.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-17-21-17-image.png" title="" alt="" data-align="center">

- Mỗi cách mã sẽ tương ứng với một hoán vị của bảng chữ và hoán vị đó chính là khoá của mã đã cho.

- Vét cạn: số khoá có thể có là 26! ≈ 4 × 1026 → rất khó thực hiện bằng tay . . .

- Ví dụ:
  
  - Bản rõ: I WANT COOKIES
  
  - Bản mã: B LOXD CEEUBKT

- Để ý rằng, trong mã thay thế, đặc trưng ngôn ngữ vẫn giữ nguyên
  
  - Tần suất xuất hiện của 1 kí tự giữ nguyên và có thể thấy kí tự đấy lặp lại

- Thám mã bằng phương pháp phân tích tần suất.

#### Phân tích tần suất

- Trong các ngôn ngữ, không phải tất cả các chữ trong một bảng chữ cái xuất hiện với tần suất giống nhau.

- Mỗi ngôn ngữ đều có một số đặc trưng riêng.

- Trong tiếng Anh:
  
  - Chữ E được sử dụng nhiều nhất, sau đó đến các chữ T, R, N, I, O, A, S; Một số chữ rất ít dùng như: Z, J, K, Q, X.
  
  - Ta có thể xây dựng các bảng các tần suất các chữ đơn, cặp chữ, bộ ba chữ.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-17-28-37-image.png" title="" alt="" data-align="center">

- Ví dụ:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-17-26-32-image.png" title="" alt="" data-align="center">

#### Mã Playfair

- Mã Playfair là hệ mã đối xứng, mỗi chữ sẽ được mã bằng một số chữ khác nhau tùy thuộc vào các chữ mà nó đứng cạnh.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-20-20-49-image.png" title="" alt="" data-align="center">

- Cách mã hóa:
  
  - Chia bản rõ thành từng cặp chữ. Nếu cặp 2 chữ giống nhau thì chèn vào giữa chữ lọc (thường là x). Ví dụ: balloon -> ba lx lo on
  
  - Nếu 2 chữ trong cặp cùng hàng thì mã mỗi chữ bằng chữ bên phải nó, hết hàng thì quay lại từ đầu hàng.
  
  - Nếu 2 chữ trong cặp cùng cột thì mã mỗi chữ bằng chữ bên dưới nó, hết cột thì quay lại từ đầu cột.
  
  - Còn lại, mỗi chữ trong cặp được mã bởi chữ cùng hàng với nó và cùng cột với chữ cùng cặp với nó trong ma trận khóa, "nt" → "RQ", "ea" → "IM" hoặc "JM".

- Ví dụ:
  
  - Bản rõ: instrument
  
  - Tách: in | st | ru | me | nt
  
  - Mã hóa: ga | tl | mz | cl | rq
  
  - Bản mã: gatlmzclrq

#### Mã Vigenere

- Mã Vigenere đc dùng để trải bằng tần suất các chữ xuất hiện trong bảng mã và làm mất bớt cấu của bản rõ đc thể hiện trên bảng mã.

- Mã Vigenere dùng nhiều bảng chữ để mã, tiến hành nhiều mã Caesar lên bản rõ với nhiều khóa khác nhau cho mỗi kí tự.

> Mã Vigenere khác Mã bảng mã chữ đơn ở chỗ Max Vigenere dùng nhiều bảng chữ cho mỗi kí tự còn Mã bảng chữ đơn chỉ đảo vị trí các kí tự.tự

- Giả sử khoá là một chữ có độ dài d được viết dạng K = K1K2 · · · Kd, trong đó Ki nhận giá trị nguyên từ 0 đến 25.

- Ví dụ:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-20-51-04-image.png" title="" alt="" data-align="center">

- Để mã mỗi kí tự trong bản rõ, ta lấy kí tự ở vị trí tương ứng trong khóa (lặp lại khóa nếu cần), kí tự ở khóa sẽ là sô bước tịnh tiến cho kí tự ở bản rõ. 
  
  - Ví dụ: tính từ 'a' là 0, 'd' của khóa lệch 3 => 'w' của bản rõ tịnh tiến 3 trở thành 'z'
  
  - Ví dụ 1: 'e' của khóa lệch 4 so với a => 'e' của bản rõ tịnh tiến 4 thành 'i'.

- Một  chữ của bản rõ có thể có được mã thành nhiều chữ khác => tần suất bị là phẳng. 

- Khóa có độ dài ko bằng bản rõ nên bị lặp => Lý tưởng nhất là độ dài khóa bằng độ dài bản rõ => Mã khóa tự động.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-20-59-47-image.png" title="" alt="" data-align="center">

#### Bộ đệm 1 lần (One-Time Pad)

- Mã OTP: Việc mã hóa (giải mã) được thực hiện bằng phép toán XOR từng bit giữa các bit có vị trí tương ứng ở bản rõ (bản mã) và khóa.

- Với OTP, khóa được sinh ngẫu nhiên, chỉ dùng 1 lần có độ dài bằng bản rõ.

- OTP an toàn tuyệt đối, nhưng ít được sử dụng, chỉ dùng trong trường hợp bảo mật rất cao.

- Ví dụ:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-21-04-50-image.png" title="" alt="" data-align="center">

#### Mã Rail Fence

- Viết các chữ của bản rõ theo đường chéo (hướng xuống dưới/hướng lên trên) trên một số dòng.

- Đọc các chữ theo theo từng dòng → bản mã.

- Số dòng chính là khoá.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-22-21-06-09-image.png" title="" alt="" data-align="center">
