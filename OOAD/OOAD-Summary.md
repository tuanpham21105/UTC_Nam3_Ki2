# OOAD

## Bài 1: Giới thiệu

### Tổng quan

- Có BTL

- Phân tích và thiết kế = Mô hình hoá

- Sử dụng UML, ngôn ngữ mô hình hóa thống nhất

- UML có các nhóm sơ đồ
  
  - Nhóm mô tả chức năng và yêu cầu
    
    - Use Case Diagram: ai dùng hệ thống, làm gì
  
  - Nhóm mô tả cấu trúc tĩnh
    
    - Class Diagram: mô tả lớp, thuộc tính, quan hệ
  
  - Nhóm mô tả hành vi động
    
    - Sequence Diagram: luồng tương tác đối tượng
    
    - tState Machine Diagram: vòng đời của một đối tượng

- Các sơ đồ dùng trong các góc nhìn (view) khác nhau, có 5 view, mỗi view mô tả 1 khía cạnh của hệ thống
  
  - Use case view
  
  - Logical view
  
  - Proccess view
  
  - Development view
  
  - Physical view

- Ko phải hệ thống nào cũng cần tất cả góc nhìn.

- Phân tích thiết kế hướng đối tượng là phương pháp, ko phải quy trình.

### Câu hỏi

1. 3 lợi ích của mô hình hóa trong phát triển phần mềm
   
   - Giao tiếp giữa khách hàng - phân tích - lập trình viên
   
   - Giảm độ phức tạp của hệ thống
   
   - Hỗ trợ thiết kế, bảo trì, tái sử dụng

2. So sánh OOAD với phương pháp thác nước
   
   - Ưu điểm Thác nước so với OOAD:
     
     - Các pha đc xác định rõ
     
     - Hỗ trợ tốt cho lên kế hoạch
     
     - Có kết quả sau mỗi pha
   
   - Nhược điểm Thác nước so với OOAD:
     
     - Phải hoàn chỉnh các pha trước mới sang pha sau
     
     - Xử lý lỗi muộn
     
     - Thời gian chuyển giao lâu

3. Sự khác nhau cơ bản giữa Logical View và Physical View trong UML
   
   - Logical view mô tả cấu trúc logic của hệ thống, lớp, thực thể, mối quan hệ giữa chúng.
   
   - Physical view mô tả cách triển khai hệ thống lên phần cứng.

4. Trong dự án nhỏ (ví dụ Quản lý thư viện), sơ đồ UML quan trọng nhất và không thể thiếu là sơ đồ Use case diagram, vì nó mô tả yêu cầu của hệ thống, là cơ sở cho các sơ đồ khác.

5. Góc nhìn Process trở nên quan trọng trong thiết kế hệ thống Quản lý thư viện khi cần mô tả hành vi động, luồng điều khiển khi tương tác với hệ thống quản lý thư viện. 

## Bài 2: Cơ sở lập trình hướng đối tượng

- Kiến thức cơ bản về lập trình hướng đối tượng

### Câu hỏi và bài tập

1. Encapsulation (tính đóng gói) là kỹ thuật gom nhóm dữ liệu, chỉ thực thể chứa dữ liệu đó có thể đọc và chỉnh sửa dữ liệu đó, mỗi lớp thực hiện một cách an toàn việc thay đổi dữ liệu bên trong.
   
   - Ví dụ minh họa với lớp Student: Chỉ các hàm bên trong Student mới được phép cập nhật và đọc dữ liệu *age* của Student.

2. Các khái niệm trong OOP:
   
   - Abstraction (Tính trừu tượng) là ẩn đi các chi tiết triển khai bên trong, chỉ hiển thị những gì cần thiết ra bên ngoài.
   
   - Inheritance (Tính kế thừa) là xây dựng lớp mới (lớp con) bằng cách sử dụng lại lớp đã có (lớp cha), lớp con đc kế thừa cả dữ liệu và các hành vi từ lớp cha.
   
   - Polymorphism (Tính đa hình) là khả năng 1 đối tượng có thể biểu diễn dưới nhiều hình dạng, diễn giải theo nhiều cách khác nhau, giúp ẩn đi các chi tiết triển khai khác nhau nhưng chung một "giao diện".

3. 
- Lớp Account
  
  - Thuộc tính: owner, balance
  
  - Phương thức: deposit(), withdraw()

- Xây dựng lớp SavingsAccount kế thừa từ Account, có Account là lớp cha và SavingsAccount là lớp con, theo định nghĩa tính kế thừa, lớp SavingsAccount sẽ được kế thừa tất cả thuộc tính và hành vi từ lớp cha là Account.
4. Ví dụ C++ về hệ thống Library System:

```C++
class Book {
    public:
        void read() {
            cout << "Reading from Book" << endl;
        }
}

class EBook : public Book {
    public:
        void read() {
            cout << "Reading from EBook" << endl;
        }
}
```

## Bài 3: Xác định yêu cầu

- Hiểu các yêu cầu của hệ thống cần phát triển là cơ sở cho các quá trình phân tích, thiết kế.

- Có 2 loại yêu cầu:
  
  - Yêu cầu chức năng
  
  - Yêu cầu phi chức năng

### Các bước trong xác định yêu cầu

- Thu thập yêu cầu: phỏng vấn, phiếu điều tra, tài liệu khảo sát,... đẻ nắm đc thông tin, yêu cầu của người dùng với hệ thống.

- Ghi nhận: sắp xếp, tổng hợp, viết đặc tả yêu cầu.

- Thẩm định: kiểm tra các yêu cầu đc ghi chép, đặc tả có đúng như người dùng muốn.

### Thu thập yêu cầu

- Trước khi tiến hành yêu cầu, cần:
  
  - Nắm rõ các mục tiêu
  
  - Xác định các bên liên quan
  
  - Hiểu sơ bộ các quy trình nghiệp vụ chính
  
  - Nhận diện các ràng buộc và ưu tiên

#### Phỏng vấn

- Ưu điểm:
  
  - Có thể khai thác thông tin theo chiều sâu
  
  - Có sự tham gia trực tiếp của người dùng
  
  - Có thể nhận phản hồi nhanh chóng

- Nhược điểm:
  
  - Không khai thác đc thông tin theo chiều rộng
  
  - Không có sự tích hợp nhiều nguồn 

#### Bảng hỏi (Phiếu điều tra)

- Có thể khảo sát ý kiến số đông, lấy thông tin theo chiều rộng

- Không tổng hợp đc kết quả phản hồi ngay lập tức

#### Họp nhóm

- Lưạ chọn người tham gia ở các vị trí khác nhau

- Đem lại nhiều nguồn, nhiều góc nhìn về 1 yêu cầu

- Tốn thời gian, chi phí

#### Nghiên cứu tài liệu, quan sát

- Nghiên cứu tài liệu của các tổ chức thực hiện yêu cầu tương tự.

- Quan sát thường ko đủ thông tin, là phương pháp bổ sung cho các phương pháp khác.

### Ghi nhận yêu cầu

- Sau khi thu thập yêu cầu cần ghi nhận bằng đặc tả và liệt kê các yêu cầu:
  
  - Vấn đề của hệ thống hiện tại
  
  - Mục tiêu của hệ thống mới
  
  - Phạm vi
  
  - Các yêu cầu chức năng, phi chức năng
  
  - Ràng buộc hệ thống   
  
  - Phụ lục

### Thẩm định yêu cầu

- Kiểm tra yêu cầu thu thập được, đối chiếu yêu cầu với nhiều nguồn nhằm hạn chế sai sót, mâu thuẫn.

### Bài tập và câu hỏi

1. Từ những điều Annie nói trong cuộc phỏng vấn, những vấn đề bất cập ở hệ thống hiện tại là:
   
   - Khi khách thuê nhiều xe, phải viết lại nhiều lần thông tin của cùng 1 khách lên nhiều thẻ thuê xe khác nhau.

2. Những bước chính để xác định yêu cầu là:
   
   - Thu thập yêu cầu
   
   - Ghi nhận yêu cầu
   
   - Thẩm định yêu cầu

3. Tài liệu cần có:
   
   - Trước buổi phỏng vấn:
     
     - Nội dung phỏng vấn
     
     - Câu hỏi phỏng vấn
     
     - Kế hoạch phỏng vấn
   
   - Sau buổi phỏng vấn:
     
     - Tóm tắt sau phỏng vấn

4. Nên sử dụng bảng hỏi khi cần lấy thông tin theo chiều rộng, khảo sát từ số đông, chi phí vừa phải và ko cần tổng hợp kết quả phản hồi ngay lập tức.

5. Các phần chính của đặc tả yêu cầu:
   
   - Vấn đề của hệ thống hiện tại
   
   - Mục tiêu của hệ thống mới
   
   - Phạm vi
   
   - Các yêu cầu chức năng, phi chức năng
   
   - Ràng buộc hệ thống
   
   - Phụ lục

6. Có 2 loại yêu cầu:
   
   - Chức năng: mô tả các chức năng mà hệ thống phải thực hiện
   
   - Phi chức năng: mô tả thuộc tính chất lượng vả **ràng buộc của hệ thống**.

7. Yêu cầu của Hệ thống Quản lý thư viện:
   
   - Chức năng:
     
     - Mượn, trả sách
     
     - Quản lý thành viên
     
     - Quản lý sách trong kho
     
     - Đăng ký thành viên
     
     - Phạt thành viên trả sách ko đúng hạn
   
   - Phi chức năng:
     
     - Hiệu năng: Hệ thống hỗ trợ tối thiểu 1000 người dùng đồng thời
     
     - Tính khả dụng: Hệ thống luôn sẵn sàng hoạt động 24/7, bao gồm cả ngoài giờ hành chính.
     
     - Tính bảo mật: Chỉ người có thẩm quyền mới được phép cập nhật kho sách, thành viên; độc giả chỉ được phép tra cứu và xem thông tin sách.
     
     - Khả năng mở rộng: Hỗ trợ mở rộng hệ thống quản lý sách mà ko ảnh hưởng tới tính khả dụng.
     
     - Thân thiện với người dùng: Giao diện dễ dùng, sử dụng thuận lợi.

## Bài 4: Mô hình ca sử dụng

- Mô hình ca sử dụng bao gồm:
  
  - Các sơ đồ ca sử dụng
  
  - Mô tả ca sử dụng

- Tác dụng:
  
  - Hiểu rõ yêu cầu và phạm vi hệ thống từ góc nhìn người dùng
  
  - Công cụ giao tiếp giữa các bên
  
  - Phương tiện tổ chức, cấu trúc, làm tài liệu

### Sơ đồ ca sử dụng

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-17-32-53-image.png" title="" alt="" data-align="center">

- Bao gồm:
  
  - Tác nhân: Đối tượng bên ngoài có tác động vào các ca sử dụng
    
    - Lưu ý: Tác nhân ko phải một người, đối tượng cụ thể mà là một vai trò, nó là thể hiện cho một nhóm đối tượng có cùng chức năng khi tương tác với hệ thống. Ví dụ: Thu ngân là một vai trò, còn Thu ngân Ng Văn A là một đối tượng.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-17-31-20-image.png" title="" alt="" data-align="center">
  
  - Ca sử dụng: Mô tả các tương tác giữa các đối tác với hệ thống sẽ xây dựng.
    
    - Mỗi ca sử dụng cần thỏa mãn một mục tiêu của tác nhân

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-17-32-01-image.png" title="" alt="" data-align="center">

- Đặc điểm mô hình ca sử dụng:
  
  - Thể hiện **góc nhìn từ bên ngoài**của người dùng về chức năng mà hệ thống cần thực  hiện
  
  - Mỗi ca sử dụng thể hiện **một nhiệm vụ chính** hoặc **một nhóm các chức năng chính**
  
  - Mỗi ca sử dụng phải đc liên kết với ít nhất 1 tác nhân
  
  - Mỗi ca sử dụng phải thực hiện một hành vi dẫn đến 1 kết quả cụ thể

### Xác định các ca sử dụng

- 2 cách chính xác định ca sử dụng:
  
  - Thông qua kịch bản, văn bản
  
  - Thông qua các tác nhân

#### Xác định thông qua kịch bản

- Xem xét từng kịch bản hoạt động như nào

- Mỗi ca sử dụng là thể hiện của một nhóm các kịch bản có cùng mục tiêu

#### Xác định thông qua các tác nhân

- Tìm các tác nhân của hệ thống và các nhiệm vụ mà từng tác nhân cần thực hiện

### Mô tả ca sử dụng

- Ko có cấu trúc thóng nhất mô tả ca sử dụng nhưng thường sẽ có cấu trúc sau:
  
  1. Tên ca sử dụng
  
  2. Các tác nhân liên hệ với ca sử dụng
  
  3. Mục tiêu
  
  4. Mô tả tổng quan
  
  5. Các yêu cầu tham khảo
  
  6. Tiền điều kiện
  
  7. Dòng sự kiện chính
  
  8. Dòng sự kiện phụ
  
  9. Hậu điều kiện

### Quan hệ giữa các ca sử dụng

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-18-17-51-image.png" title="" alt="" data-align="center">

- Có 2 loại quan hệ:
  
  - A <<include>> B: A bao gồm B, khi mà thực hiện A sẽ thực hiện cả B trong quy trình của nó.
    
    - Lưu ý: dùng khi cần tách ra 1 chức năng chung của nhiều ca sử dụng.
  
  - A <<extend>> B: A là mở rộng của B, A là các chức năng mở rộng, A được thực hiện dưới một điều kiện nào đó khi thực hiện B.

### Câu hỏi và bài tập

1. Mô hình ca sử dụng:
   
   - Gồm:
     
     - Các sơ đồ ca sử dụng
     
     - Mô tả ca sử dụng
   
   - Tác dụng:
     
     - Hiểu rõ yêu cầu và phạm vi hệ thống từ góc nhìn của người sử dụng
     
     - Là công cụ giao tiếp giữa các bên liên quan
     
     - Là một phương tiện để tổ chức, cấu trúc và làm tài liệu

2. Các phương pháp để xác định ca sử dụng:
   
   - Thông qua kịch bản, văn bản
   
   - Thông qua các tác nhân

3. Chức năng được coi là 1 ca sử dụng khi:
   
   - Thể hiện **góc nhìn từ bên ngoài** của người dùng về chức năng mà hệ thống cần thực hiện
   
   - Mỗi ca sử dụng thể hiện **một nhiệm vụ chính** hoặc **một nhóm các chức năng chính**
   
   - Mỗi ca sử dụng phải đc liên kết với ít nhất 1 tác nhân
   
   - Mỗi ca sử dụng phải thực hiện một hành vi dẫn đến 1 kết quả cụ thể

4. Mô tả rút gọn ca sử dụng là là cách trình bày ngắn gọn nội dung của một ca sử dụng. Nó cho biết tác nhân tương tác với hệ thống để đạt mục tiêu gì. Nó ko bao gồm cá bước xử lý chi tiết.

5. Mô tả đầy đủ ca sử dụng:
   
   - Cần có:
     
     1. Tên ca sử dụng
     
     2. Các tác nhân liên hệ với ca sử dụng
     
     3. Mục tiêu
     
     4. Mô tả tổng quan
     
     5. Các yêu cầu tham khảo
     
     6. Tiền điều kiện
     
     7. Dòng sự kiện chính
     
     8. Dòng sự kiện phụ
     
     9. Hậu điều kiện
   
   - Cần mô tả đầy đủ ca sử dụng khi 

6. Các loại quan hệ giữa các ca sử dụng:
   
   - A <> B: A bao gồm B, khi mà thực hiện A sẽ thực hiện cả B trong quy trình của nó.
     
     - Lưu ý: dùng khi cần tách ra 1 chức năng chung của nhiều ca sử dụng.
   
   - A <> B: A là mở rộng của B, A là các chức năng mở rộng, A được thực hiện dưới một điều kiện nào đó khi thực hiện B.
   
   - Ví dụ: Ca sử dụng "Cho mượn sách" sẽ thực hiện cả "Tìm kiếm sách trong kho".

7. - Một ca sử dụng tốt cần đảm bảo những yếu tố sau: 
     
     - Thỏa mãn một mục tiêu của tác nhân
   
   - Một sơ đồ ca sử dụng đầy đủ và rõ ràng khi:
     
     - Xác định đầy đủ tác nhân
     
     - Liệt kê được các ca sử dụng 
     
     - Quan hệ giữa các chức năng phù hợp

## Bài 5: Biểu đồ hành động

### Khái niệm

- Biểu đồ hành động là công cụ mô hình các quá trình gồm nhiều bước thực hiện.

- Dùng để mô tả:
  
  - Luông công việc
  
  - Kịch bản 1 ca sử dụng
  
  - Hoạt động của chức năng
  
  - Thuật toán

### Các thành phần của biểu đồ hành động

- Thành phân cơ bản:
  
  - Hành động <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-39-38-image.png" title="" alt="" data-align="center">
  
  - Điểm bắt đầu <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-40-05-image.png" title="" alt="" data-align="center">
  
  - Điểm dừng <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-40-28-image.png" title="" alt="" data-align="center">
  
  - Hướng <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-41-12-image.png" title="" alt="" data-align="center">
  
  - Điểm gộp của rẽ nhánh <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-41-48-image.png" title="" alt="" data-align="center">
  
  - Điều kiện <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-42-08-image.png" title="" alt="" data-align="center">
  
  - Điểm rẽ nhánh/hợp nhất của tiến trình song song <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-44-25-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả chi tiết chức năng đơn giản:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-46-35-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả rẽ nhánh theo điều kiện:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-47-38-image.png" title="" alt="" data-align="center">

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-48-08-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả vòng lặp giữa các hành động:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-48-50-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả vòng lặp trong 1 hành động, dùng dấu "*" ở cuối:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-50-06-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả hành động song song:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-50-33-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả phân làn:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-51-10-image.png" title="" alt="" data-align="center">

- Ví dụ phân hoạch biểu đồ, đóng gói và dán nhãn một phần biểu đồ để có thể tái sử dụng phần đóng gói:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-52-18-image.png" title="" alt="" data-align="center">

- Ví dụ mô tả tương tác với các đối tượng, các đối tượng được kí hiệu bằng hình vuông với tên đối tượng được gạch dưới:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-01-23-20-53-15-image.png" title="" alt="" data-align="center">

### Câu hỏi và bài tập

1. Mục đích biểu đồ hành động là dùng để mô hình các quá trình phức tạp gồm nhiều bước thực hiện.

2. Những loại quá trình có thể mô tả bằng biểu đồ hành động là:
   
   - Luông công việc
   
   - Kịch bản 1 ca sử dụng
   
   - Hoạt động của chức năng
   
   - Thuật toán

3. Biểu đồ hành động và sơ đồ khối (Flowchart) gần tương tự nhau, điểm khác là Biểu đồ hành động phải tuân theo chuẩn UML.

4. Những yếu tố trong hầu hết các ngôn ngữ lập trình có thể được mô tả trong biểu đồ hành động gồm:
   
   - Rẽ nhánh
   
   - Điều kiện
   
   - Vòng lặp
   
   - Phân hoạch đóng gói và dán nhãn tương tự với hàm trong lập trình
   
   - Thực hiện tiến trình song song

## Bài 6: Phân tích đối tượng

### Phân tích đối tượng

- Vai trò:
  
  - Là cầu nối giữa phân tích yêu cầu và thiết kế
  
  - Giúp hiểu rõ:
    
    - Những thực thể nghiệp vụ nào cần quản lý
    
    - Chúng có quan hệ với nhau ra sao
  
  - Tạo nền cho xây dựng biểu đồ lớp phân tích
  
  - Mô hình phân tích: tập trung vào khái niệm nghiệp vụ

- Mục tiêu của phân tích đối tượng:
  
  - Xác định các lớp khái niệm trong miền bài toán
  
  - Ghi nhận các thuộc tính (attributes) quan trọng và mối quan hệ
  
  - Tuyệt đối chưa quan tâm đến chi tiết giao diện và cơ sở dữ liệu để tránh lỗi "Thiết kế sớm"

- Đầu vào:
  
  - Mô tả use case, các kịch bản
  
  - Thuật ngữ nghiệp vụ từ các bên
  
  - Tài liuệ hiện có

- Đầu ra:
  
  - Danh sách ban đầu các đối tượng tiềm năng
  
  - Đặc tả sơ bộ về các đối tượng đó
  
  - Biểu đồ lớp phân tích

### Đối tượng

- Đối tượng là các thực thể trong thế giới thực (vật lý hoặc khái niệm)

- Mỗi đối tượng gồm:
  
  - Trạng thái (giá trị thuộc tính)
  
  - Ứng xử (Các hành động)
  
  - Định danh (Duy nhất trong bộ nhớ)

### Sơ đồ đối tượng

- Đối tượng A phụ thuộc vào đối tượng B

- Ví dụ: Khách hàng ann thuê xe đạp ladies[8]

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-32-08-image.png" title="" alt="" data-align="center">

- Ưu điểm:
  
  - Mô tả quan hệ giữa các đối tượng
  
  - Làm rõ hơn các hệ thống phức tạp **tại 1 thời điểm**

### Lớp

- Lớp là định nghĩa trừu tượng (khuôn mẫu) cho các đối tượng có chung đặc tính

- Một đối tượng là 1 thể hiện cụ thể (instance) của một lớp

- Lớp giúp thực hiện trừu tượng hóa dữ liệu, bao gói và che giấu thông tin

### Sơ đồ lớp

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-36-05-image.png" title="" alt="" data-align="center">

- Lớp A liên kết với lớp B tương đương với việc các đối tượng của A **có thể tương tác** được với các đối tượng của B

### Các liên kết giữa các lớp

- Kết hợp (Association): Liên kết cơ bản, cho phép các đối tượng tương tác với nhau. Đi kèm với **Tính bội (Multiplicity)** để xác định giới hạn số lượng đối tượng tham gia.
  
  - Ví dụ: Một hoặc nhiều khách hàng có thể thuê một hoặc nhiều xe đạp

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-42-17-image.png" title="" alt="" data-align="center">

- Liên kết bội (Multiplicity):
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-43-28-image.png" title="" alt="" data-align="center">

- Kết tập (Aggregation): Quan hệ "tổng thể - thành phần", mô tả thường có các cụm từ "A là một phần của B" hoặc "B gồm có A, C,..."
  
  - Ví dụ: Ô tô gồm có 4 bánh, 2 hoặc 4 hoặc 5 cánh cửa và 1 động cơ

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-46-50-image.png" title="" alt="" data-align="center">

- Gộp (Composition): Một dạng kết tập chặt chẽ hơn, trong đó đối tượng thành phần ko thể tồn tại độc lập nếu đối tượng tổng thể bị hủy bỏ
  
  - Ví dụ: Người máy gồm có cánh tay robot, bánh xe robot

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-49-05-image.png" title="" alt="" data-align="center">

- Kế thừa (Inheritance): Quan hệ "là một" (is-a) hoặc "là một loại" (is-a-kind-of), giúp tổng quát hóa các đặc tính chung vào lớp cha và chuyên biệt hóa ở lớp con.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-10-51-30-image.png" title="" alt="" data-align="center">

- Lớp kết hợp (Association Class): Sử dụng khi bản thân mối liên kết giữa 2 lớp chứa các thuộc tính riêng
  
  - Ví du: Khách hàng thuê xe đạp, lớp thuê chứa ngày bắt đầu thuê, ngày kết thúc,...

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-11-03-32-image.png" title="" alt="" data-align="center">

### Tiêu chuẩn của một lớp thiết kế tốt

- Các lớp nên phản ánh đúng đối tượng trong phạm vi bài toán

- Một lớp cần có cả dữ liệu và hành vi, tránh lớp chỉ toàn dữ liệu hoặc chỉ toàn hành vi

- Lớp phải có tínhcố kết cao, nên tập trung vào chỉ 1 trách nghiệm chính

### Câu hỏi

1. Sự khác nhau giữa đối tượng và lớp:
   
   - **Lớp:** Là một định nghĩa trừu tượng của các đối tượng có cùng những đặc tính chung.
     
     - Ký hiệu: Thể hiện bằng hình chữ nhật chia làm 3 phần: Tên lớp (trên cùng), Các thuộc tính (giữa) và Các thao tác/phương thức (dưới cùng).
       
       <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-11-19-59-image.png" title="" alt="" data-align="center">
   
   - **Đối tượng:** Là một thể hiện cụ thể của một lớp.
     
     - Ký hiệu: Thể hiện bằng hình chữ nhật gồm tên đối tượng và tên lớp (thường gạch chân), theo sau là các giá trị thuộc tính cụ thể (ví dụ: `ann :Customer`)
       
       <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-11-20-27-image.png" title="" alt="" data-align="center">

2. Các đặc trung của một đối tượng là:
   
   - Trạng thái: được xác định bởi giá trị hiện tại của các thuộc tính mà đối tượng đang nắm giữ
   
   - Ứng xử: Thể hiện qua các hành động và thao tác mà đối tượng có thể thực hiện
   
   - Định danh: Tính duy nhất của mỗi đối tượng trong bộ nhớ, giúp phân biệt nó với các đối tượng khác dù chúng có cùng trạng thái

3. Các đối tượng giao tiếp với nhau qua liên kết, khi lớp A liên kết với lớp B, đối tượng của lớp A có thể tương tác với đối tượng của lớp B

4. Bốn loại liên kết giữa các lớp:
   
   - **Kết hợp (Association):** Là liên kết cơ bản và phổ biến nhất thể hiện sự tương tác giữa hai lớp. Nó thường đi kèm với **tính bội (multiplicity)** để xác định giới hạn số lượng đối tượng tham gia (ví dụ: 1, 0..*, 1..*).
   
   - **Kết tập (Aggregation):** Thể hiện quan hệ "tổng thể - thành phần" (whole-part). Trong mô tả thường có cụm từ "là một phần của" hoặc "gồm có" (ví dụ: Xe hơi gồm có động cơ, bánh xe).
   
   - **Gộp (Composition):** Là một dạng kết tập chặt chẽ hơn. Điểm khác biệt quan trọng là đối tượng thành phần không thể tồn tại độc lập nếu đối tượng tổng thể bị hủy bỏ (ví dụ: Cơ thể và các bộ phận như đầu, tay, chân).
   
   - **Kế thừa (Inheritance):** Thể hiện quan hệ "là một" (is-a) hoặc "là một loại" (is-a-kind-of). Nó giúp tổng quát hóa (Generalization) các đặc tính chung vào lớp cha và chuyên biệt hóa (Specialization) ở các lớp con.

5. Tiêu chuẩn để coi là một lớp tốt:
   
   - **Phản ánh đúng miền bài toán (Problem domain)**
   
   - **Đầy đủ chức năng (Functionality)**
   
   - **Tính cố kết cao (High Cohesion)**

6. Không nên thêm các lớp `BorrowForm` và `LoanController` vào. Vì những lớp này không phải là lớp miền nghiệp vụ (domain class) -> lỗi Thiết kế sớm

## Bài 7: Xác định lớp và thuộc tính

### Biểu đồ lớp

- **Tầm quan trọng:** Biểu đồ lớp là trung tâm của quá trình phân tích thiết kế

- **Đặc điểm:** Nó mô hình hóa cấu trúc của hệ thống (structural model), thể hiện các đối tượng và cách chúng liên kết với nhau.

- **Tiến trình xây dựng:** Biểu đồ lớp được xây dựng dần dần, bao gồm các lớp thực thể (miền bài toán), các lớp giao diện và các lớp điều khiển.

- **Các bước chính:**
  
  1. Xác định đối tượng và lớp tương ứng.
  
  2. Xác định các thuộc tính cho từng lớp.
  
  3. Xác định mối quan hệ (multiplicity, role).
  
  4. Phân bổ trách nhiệm cho các lớp.

### Kỹ thuật xác định lớp

- 2 phương pháp:
  
  - **Dựa trên phân loại (Categorization):** Các khái niệm được chuyển thành đối tượng dựa trên thực thể vật chất (Xe, Sách), vai trò (Khách hàng, Giáo viên), các tương tác (Thuê, Mượn) hoặc tổ chức (Công ty, Khoa).
  
  - **Phân tích danh từ (Noun Analysis):** Dựa trên văn bản mô tả hệ thống, các danh từ/cụm danh từ thường là đối tượng hoặc thuộc tính, trong khi động từ thường là các thao tác (operations).
    
    - **Quy trình:** Tìm danh từ -> Gạch chân -> Loại bỏ danh từ không phù hợp -> Sắp xếp thành lớp ứng viên.

### Tiêu chí giữ/bỏ lớp ứng viên

- Để tối ưu hóa mô hình, cần sàng lọc các lớp ứng viên:
  
  - **Giữ lại nếu:** Là thực thể nghiệp vụ quan trọng, bền vững, có dữ liệu riêng và cần hành vi đi kèm.
  
  - **Loại bỏ nếu:**
    
    - Chỉ là thuộc tính của một lớp khác (Địa chỉ, Màu sắc).
    - Là chi tiết kỹ thuật/giao diện (Biểu mẫu, Cơ sở dữ liệu).
    - Là hành động (Mượn, Trả - nên là phương thức).
    - Nằm ngoài phạm vi hệ thống hoặc trùng lặp ý nghĩa.

### Xác định các thuộc tính

- Thuộc tính được xác định qua các danh từ chỉ giá trị (không đủ độc lập để làm lớp) và là các thành phần thuộc về lớp mà nó mô tả.

### Câu hỏi

1. Tầm quan trọng của mô hình hóa cấu trúc hệ thống (Structural modeling)
- **Định nghĩa cấu trúc:** Mô hình hóa cấu trúc giúp định nghĩa và thể hiện cách các đối tượng trong hệ thống được tổ chức và liên kết với nhau.

- **Vai trò trung tâm:** Biểu đồ lớp (công cụ chính của mô hình hóa cấu trúc) được coi là trung tâm của toàn bộ quá trình phân tích và thiết kế hệ thống.

- **Sự tiến triển:** Mô hình này không cố định mà được xây dựng, bồi đắp dần dần qua các giai đoạn phát triển, từ các lớp thực thể nghiệp vụ đến các lớp giao diện và điều khiển.
2. Các bước chính để xây dựng biểu đồ lớp
- Theo tài liệu, quy trình xây dựng biểu đồ lớp gồm 4 bước chính:
  
  - **Xác định đối tượng:** Tìm các đối tượng trong phạm vi bài toán và xác định các lớp tương ứng.
  
  - **Xác định thuộc tính:** Tìm các đặc điểm, thông tin cần lưu trữ cho từng lớp.
  
  - **Xác định mối quan hệ:** Thiết lập các liên kết giữa các lớp, xác định tính bội (multiplicity) và vai trò (role) của từng lớp trong mối quan hệ đó.
  
  - **Phân bổ trách nhiệm:** Xác định các hành vi hoặc thao tác mà mỗi lớp phải thực hiện.
3. Bốn loại đối tượng thường gặp khi phân loại
- Khi thực hiện xác định lớp dựa trên phương pháp phân loại (categorization), 4 loại đối tượng thường được chuyển thành lớp bao gồm:
  
  - **Các thực thể vật chất:** Ví dụ như Xe (Bike), Sách (Book).
  
  - **Các vai trò:** Ví dụ như Nhân viên tiếp tân (Receptionist), Khách hàng (Customer), Giáo viên (Teacher).
  
  - **Các tương tác:** Ví dụ như việc Thuê (Hire), việc Mượn (Loan).
  
  - **Các tổ chức:** Ví dụ như Công ty (Company), Khoa (Faculty).
4. Kỹ thuật phân tích danh từ (Noun analysis)
- Đây là kỹ thuật dựa trên đề nghị của Abbott để tìm kiếm các thành phần của biểu đồ lớp từ văn bản mô tả hệ thống. Quy trình thực hiện gồm:
  
  - **Nguyên tắc cơ bản:** Các danh từ/cụm danh từ thường là đối tượng hoặc thuộc tính; các động từ thường là các thao tác (operations).
  
  - **Các bước thực hiện:**
    
    1. Tìm các danh từ trong mô tả bài toán hoặc kịch bản use case.
    2. Gạch chân các danh từ và cụm danh từ đó.
    3. Sàng lọc và loại bỏ các danh từ không phù hợp (như danh từ chỉ thuộc tính, chi tiết kỹ thuật, hoặc danh từ trùng lặp).
    4. Sắp xếp những danh từ còn lại thành các lớp ứng viên.
5. Bốn loại liên kết (quan hệ) giữa các lớp
- Dựa trên nội dung các ví dụ và phần tinh chỉnh trong tài liệu, các loại liên kết chính bao gồm:
  
  - **Kết hợp (Association):** Mối quan hệ tương tác cơ bản giữa các lớp (ví dụ: Khách hàng và Thanh toán).
  
  - **Lớp kết hợp (Association Class):** Khi bản thân mối liên kết chứa thông tin riêng (ví dụ: lớp Thuê - Hire chứa ngày thuê, ngày trả).
  
  - **Kế thừa (Inheritance/Generalization):** Quan hệ giữa lớp tổng quát và lớp chuyên biệt khi chúng có nhiều điểm chung (ví dụ: Bike và SpecialistBike).
  
  - **Kết tập/Gộp (Aggregation/Composition):** Mối quan hệ phần thân - thành phần (được thể hiện qua ký hiệu hình thoi trong các sơ đồ bài tập).
6. Mối liên hệ giữa Ca sử dụng (Use case) và Biểu đồ lớp
- **Nguồn dữ liệu:** Kịch bản của Use case là nguồn tài liệu quan trọng để thực hiện phân tích danh từ nhằm xác định lớp và thuộc tính.

- **Xây dựng lớp điều khiển:** Các lớp giao diện và lớp điều khiển trong biểu đồ lớp thường được xây dựng dựa trên nhu cầu thực hiện của từng ca sử dụng cụ thể.

- **Phân bổ trách nhiệm:** Use case giúp xác định các hành vi cần thiết mà các lớp phải phối hợp thực hiện, từ đó giúp phân bổ trách nhiệm (operations) cho từng lớp một cách chính xác.

## Bài 8: Kỹ thuật CRC

### Khái niệm CRC

- CRC là viết tắt cho ba thành phần cốt lõi:
  
  - **C - Class (Lớp):** Tên lớp đang được xem xét.
  
  - **R - Responsibility (Trách nhiệm):** Những nvu, chức năng mà lớp đó phải thực hiện.
  
  - **C - Collaboration (Cộng tác):** Các lớp khác mà lớp này cần tương tác hoặc nhờ cậy để hoàn thành trách nhiệm của mình.

- Kỹ thuật thẻ CRC được đưa vào để giúp xác định các hoạt động của một lớp thông qua việc phân tích trách nhiệm của từng lớp.

### Lí do và cách làm thẻ CRC

- **Lý do sử dụng:**
  
  - Là công cụ đơn giản, trực quan giúp phân chia trách nhiệm rõ ràng giữa các lớp.
  - Rất hữu hiệu cho việc thảo luận nhóm (brainstorming) trong quá trình phân tích.

- **Cách làm:**
  
  - Sử dụng các tờ bìa riêng biệt (thường có kích thước 10x15 cm) cho mỗi lớp.
  - Chia thẻ thành 3 phần tương ứng với Class, Responsibility và Collaborator.
  - Liệt kê các trách nhiệm và xác định các lớp tương tác tương ứng.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-21-41-56-image.png" title="" alt="" data-align="center">

- Ví dụ: 
  
  - **Lớp Customer (Khách hàng):**
    
    - *Trách nhiệm:* Cung cấp thông tin khách hàng, theo dõi giao dịch thuê (cộng tác với lớp `Hire`), thêm các đối tượng thanh toán mới (cộng tác với lớp `Payment`).
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-21-43-32-image.png" title="" alt="" data-align="center">

### Xác định hoạt động

- Từ các "trách nhiệm" đã ghi trên thẻ CRC, người phân tích sẽ tìm ra các phương thức cụ thể để đưa vào sơ đồ lớp.

- Ví dụ:
  
  - Trách nhiệm "Cung cấp thông tin khách hàng" sẽ là hoạt động (phương thức) getCustomer()
  
  - Trách nhiệm "Theo dõi các giao dịch thuê" sẽ là hoạt động getCustomerHireTransactions()
  
  - Trách nhiệm "Thêm hóa đơn" sẽ là hoạt động addCustomerNewPayment(payment)

### Đánh giá kỹ thuật CRC

- **Đặc điểm:** Giúp xác định rõ vai trò từng lớp, linh hoạt và rất thuận tiện cho làm việc nhóm.

- **Hạn chế:** Không mô tả được chi tiết **trình tự tương tác** (thứ tự trước sau) giữa các đối tượng.

- **Giải pháp khắc phục:** Sử dụng kết quả từ bộ thẻ CRC làm đầu vào để xây dựng các **sơ đồ tương tác (Interaction Diagrams)** như sơ đồ tuần tự.

### Câu hỏi

1. 
- **Thẻ CRC là gì?** CRC là viết tắt của **C**lass (Lớp), **R**esponsibility (Trách nhiệm) và **C**ollaboration (Cộng tác), dùng để mô hình hóa các thành phần của lớp.

- **Tại sao sử dụng trong OOAD?** 
  
  - Là công cụ đơn giản, trực quan
  
  - Xác định trách nhiệm của lớp
  
  - Phân tích trách nhiệm rõ ràng giữa các lớp
  
  - Brainstorming trong phân tích, thảo luận nhóm
2. **Các yếu tố nắm bắt:** Một thẻ CRC nắm bắt ba thành phần chính:
- **Tên lớp (Class name):** Xác định thực thể đang được xem xét.

- **Trách nhiệm (Responsibility):** Các nhiệm vụ, chức năng hoặc thông tin mà lớp đó phải thực hiện hoặc nắm giữ.

- **Cộng tác (Collaboration):** Danh sách các lớp khác mà lớp hiện tại cần tương tác để hoàn thành trách nhiệm của mình.
3. **Số lượng trách nhiệm:** Phụ thuộc vào giới hạn kích thước thẻ, buộc phải chọn ra những trách nhiệm quan trọng nhất.

4. Kỹ thuật CRC rất phù hợp và thường được sử dụng cho việc thảo luận nhóm hoặc động não (brainstorming) trong giai đoạn phân tích.

5. 
- **Đặc điểm:** Giúp xác định rõ vai trò từng lớp, linh hoạt và rất thuận tiện cho làm việc nhóm.

- **Hạn chế:** Không mô tả được chi tiết **trình tự tương tác** (thứ tự trước sau) giữa các đối tượng.

## Bài 9: Các sơ đồ tương tác

### Vai trò sơ đồ tương tác

- Thẻ CRC chỉ cho thấy quan hệ và trách nhiệm giữa các lớp, ko thể hiện được trình tự tương tác cụ thể giữa các đối tượng. Sơ đồ tương tác được dùng để làm việc đó:

- Vai trò:
  
  - **Mô tả sự tương tác:** Thể hiện cách các đối tượng hợp tác với nhau để hoàn thành một ca sử dụng (use case) thông qua việc trao đổi các thông điệp (messages).
  
  - **Vị trí trong mô hình hóa:** Thuộc trục mô hình hóa chức năng (Functional) và mô hình hóa động (Dynamic).
  
  - **Lợi ích:** Giúp kiểm tra lại việc phân công trách nhiệm cho các lớp và là đầu vào quan trọng cho bước xây dựng Biểu đồ lớp thiết kế (Design Class Diagram).

### Các loại sơ đồ tương tác chính

- 2 loại sơ đồ có **tác dụng tương đương** nhưng **nhấn mạnh khía cạnh khác nhau**:
  
  - **Sơ đồ trình tự (Sequence diagram):** Nhấn mạnh vào **trình tự thời gian** của các tương tác.
  
  - **Sơ đồ giao tiếp (Communication diagram):** Nhấn mạnh vào **cấu trúc cộng tác** giữa các đối tượng, phù hợp khi muốn làm rõ các kết nối phức tạp.

### Sơ đồ trình tự (Sequence Diagram)

- Các ký hiệu cơ bản:
  
  - **Tác nhân (Actor):** Người hoặc hệ thống bên ngoài tham gia vào tương tác.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-11-32-image.png" title="" alt="" data-align="center">
  
  - **Đối tượng/Lớp (Object/Class):** Thực thể (Lớp) tham gia gửi hoặc nhận thông điệp.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-11-47-image.png" title="" alt="" data-align="center">
  
  - **Đường sống (Lifeline):** Đường chấm dọc thể hiện sự tồn tại của đối tượng trong suốt quá trình tương tác.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-12-14-image.png" title="" alt="" data-align="center">
  
  - **Thanh kích hoạt (Activation bar):** Hình chữ nhật mỏng nằm trên lifeline cho biết khi đối tượng đang thực hiện một hành động.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-12-30-image.png" title="" alt="" data-align="center">
  
  - **Thông điệp (Message):** Mũi tên nét liền thể hiện lời gọi phương thức; mũi tên nét đứt (Return message) thể hiện kết quả trả về.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-13-06-image.png" title="" alt="" data-align="center">
  
  - **Hủy đối tượng (Object Destruction):** Dấu X ở cuối của **Đường sống** đánh dấu đối tượng đã được hủy.
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-13-17-image.png" title="" alt="" data-align="center">

- Ví dụ: Khách hàng đút thẻ vào cây ATM

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-08-14-04-image.png" title="" alt="" data-align="center">

- Các bước xây dựng sơ đồ trình tự:
1. Xác định ngữ cảnh (thường là một kịch bản trong một use case cụ thể).

2. Nhận diện các tác nhân và đối tượng tham gia.

3. Thiết lập lifeline cho từng đối tượng.

4. Viết các thông điệp tương tác và kết quả trả về dựa trên kịch bản.

5. Thêm các thanh kích hoạt để làm rõ thời gian thực thi.

### Mô hình lớp BCE (Boundary - Control - Entity)

- Để tăng tính rõ ràng, giảm sự phụ thuộc (coupling) và tăng tính cố kết (cohesion), lớp được chia thành 3 loại:
  
  - **Lớp biên (Boundary class):** Giao tiếp với người dùng hoặc hệ thống bên ngoài (thường gắn với UI, biểu mẫu).
  
  - **Lớp điều khiển (Control class):** Điều phối logic và điều khiển luồng cho một use case cụ thể.
  
  - **Lớp thực thể (Entity class):** Lưu trữ dữ liệu nghiệp vụ lâu dài, thường ánh xạ với các bảng trong cơ sở dữ liệu.

- Ví dụ: 
  
  - Lớp biên: BorrowBook
  
  - Lớp điều khiển: LoanController
  
  - Lớp thực thể: Copy, Loan
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-15-43-00-image.png" title="" alt="" data-align="center">

### Sơ đồ giao tiếp (Communication Diagram)

- Đặc điểm và Ký hiệu:
  
  - **Đối tượng:** Được vẽ bằng hình chữ nhật.
  
  - **Thông điệp:** Là mũi tên nối giữa các đối tượng.
  
  - **Thứ tự thực hiện:** Được thể hiện bằng việc đánh số thứ tự trên nhãn thông điệp (ví dụ: 1, 1.1, 2...). Nhãn bao gồm "số thứ tự + tên hành động".
  
  - **Nhãn** = Số thứ tự + Hành động. Ví dụ: 1.2. Tính toán.

- Ví dụ:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-16-12-12-image.png" title="" alt="" data-align="center">

- Quy trình xây dựng sơ đồ giao tiếp:
1. Xác định ngữ cảnh.

2. Nhận diện tác nhân và đối tượng.

3. Thêm các thông điệp tương tác.

4. Đánh số các thông điệp để làm rõ trình tự.

### Câu hỏi

1. Có hai loại sơ đồ tương tác:
- **Sơ đồ trình tự (Sequence diagram):**
  
  - **Tác dụng:** Mô tả trình tự tương tác theo thời gian của các đối tượng
  - **Cách xây dựng:** Bao gồm 5 bước: (1) Xác định ngữ cảnh kịch bản; (2) Nhận diện tác nhân/đối tượng; (3) Thiết lập đường sống (lifeline); (4) Viết các thông điệp (message) tương tác; (5) Thêm thanh kích hoạt (activation bar).
  - **Đặc điểm chính:** Nhấn mạnh trình tự thời gian.

- **Sơ đồ giao tiếp (Communication diagram):**
  
  - **Tác dụng:** Mô tả cấu trúc cộng tác giữa các đối tượng.
  - **Cách xây dựng:** Bao gồm 4 bước: (1) Xác định ngữ cảnh; (2) Nhận diện tác nhân/đối tượng; (3) Thêm thông điệp tương tác; (4) Đánh số thứ tự các thông điệp.
  - **Đặc điểm:** Nhấn mạnh cấu trúc cộng tác giữa các đối tượng.
2. **Tác dụng của sơ đồ trình tự:** Mô tả trực quan trình tự tương tác theo thời gian của các đối tượng.

3. **So sánh Sơ đồ trình tự và Sơ đồ trình tự hệ thống:**
   
   - **Sơ đồ trình tự hệ thống (System Sequence Diagram):** tập trung vào sự tương tác giữa tác nhân bên ngoài và hệ thống (ví dụ: Actor và máy ATM).
   
   - **Sơ đồ trình tự (chi tiết):** mô tả sự tương tác giữa các đối tượng nội bộ (như các lớp Boundary, Control, Entity) để thực hiện yêu cầu của tác nhân.

4. **So sánh Sơ đồ trình tự và Sơ đồ giao tiếp:**
   
   - Sơ đồ trình tự nhấn mạnh trình tự thời gian, sơ đồ tương giao tiếp nhấn mạnh cấu trúc cộng tác giữa các đối tượng
   - **Ưu điểm của Sơ đồ trình tự:** Thể hiện rất rõ ràng trình tự thời gian và luồng xử lý của kịch bản.
   - **Ưu điểm của Sơ đồ giao tiếp:** Giúp người xem dễ dàng nhận thấy mối quan hệ tổng thể và các kết nối phức tạp giữa nhiều đối tượng với nhau.

## Bài 10: Sơ đồ trạng thái

### Vai trò sơ đồ trạng thái

- Sơ đồ này dùng để mô tả ứng xử của các đối tượng trong một lớp cụ thể. Nó thể hiện các trạng thái (state) khác nhau mà đối tượng trải qua trong vòng đời của mình và những sự kiện (event) gây ra sự thay đổi giữa các trạng thái đó.

### Ký hiệu của sơ đồ

- **Trạng thái (State):** Thể hiện một tình huống hoặc điều kiện của đối tượng (ví dụ: tài khoản ngân hàng ở trạng thái "In credit" - còn tiền, hoặc "Overdrawn" - thấu chi).

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-17-58-58-image.png" title="" alt="" data-align="center">

- **Sự kiện (Event):** Tác nhân gây ra sự thay đổi trạng thái.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-17-59-16-image.png" title="" alt="" data-align="center">

- **Chuyển tiếp (Transition):** Mũi tên chỉ hướng thay đổi từ trạng thái này sang trạng thái khác.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-17-59-30-image.png" title="" alt="" data-align="center">

- **Các ký hiệu khác:**
  
  - **Start state:** Trạng thái khởi đầu (hình tròn đen đặc).
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-17-59-43-image.png" title="" alt="" data-align="center">
  
  - **Stop state:** Trạng thái kết thúc (hình tròn có vòng bao ngoài).
  
  <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-17-59-54-image.png" title="" alt="" data-align="center">
  
  - **Guard (Điều kiện):** Kiểm tra điều kiện trước khi chuyển trạng thái.
  
  - **Action (Hành động):** Xảy ra khi có sự chuyển tiếp.

- Ví dụ:

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-18-25-23-image.png" title="" alt="" data-align="center">

### Một số loại trạng thái đặc biệt

- **Trạng thái hành động (Activity state):** Mô tả một hoạt động đang diễn ra.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-18-26-25-image.png" title="" alt="" data-align="center">

- **Trạng thái bên trong (Internal state):** Các xử lý diễn ra bên trong bản thân trạng thái đó.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-18-26-41-image.png" title="" alt="" data-align="center">

- **Siêu trạng thái (Superstate):** Một trạng thái lớn bao hàm các trạng thái con bên trong nhằm đơn giản hóa sơ đồ khi có nhiều trạng thái chia sẻ chung các sự kiện chuyển tiếp.

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-16-18-26-55-image.png" title="" alt="" data-align="center">

### Quy trình xây dựng sơ đồ trạng thái

1. **Xác định ngữ cảnh:** Xác định rõ đối tượng cụ thể nào cần được mô tả ứng xử.

2. **Xác định trạng thái đầu và cuối:** Tìm điểm bắt đầu và kết thúc của vòng đời đối tượng.

3. **Xác định thứ tự các trạng thái:** Sắp xếp các trạng thái theo trình tự logic.

4. **Xác định sự kiện và điều kiện:** Tìm các tác nhân gây chuyển dịch và các ràng buộc đi kèm.

5. **Vẽ biểu đồ:** Kết nối các thành phần lại thành sơ đồ hoàn chỉnh.

### Câu hỏi

1. Tác dụng của sơ đồ trạng thái:
- Sơ đồ trạng thái dùng để mô tả **ứng xử của đối tượng** trong một lớp cụ thể.
- Nó giúp thể hiện các **trạng thái (state)** khác nhau của đối tượng và những **sự kiện (event)** gây ra sự thay đổi giữa các trạng thái đó.
2. Không thể vẽ sơ đồ trạng thái cho cả hệ thống, bởi vì sơ đồ trạng thái được dùng để mô tả ứng xử của các đối tượng trong một lớp cụ thể.

3. Các bước xây dựng sơ đồ trạng thái gồm:
   
   1. **Xác định ngữ cảnh:** Xác định rõ đối tượng cụ thể nào cần được mô tả ứng xử.
   
   2. **Xác định trạng thái đầu và cuối:** Tìm điểm bắt đầu và kết thúc của vòng đời đối tượng.
   
   3. **Xác định thứ tự các trạng thái:** Sắp xếp các trạng thái theo trình tự logic.
   
   4. **Xác định sự kiện và điều kiện:** Tìm các tác nhân gây chuyển dịch và các ràng buộc đi kèm.
   
   5. **Vẽ biểu đồ:** Kết nối các thành phần lại thành sơ đồ hoàn chỉnh.

4. Sự khác nhau giữa trạng thái và sự kiện là:
- **Trạng thái (State):** Là giá trị hiện tại của các thuộc tính của đối tượng.
- **Sự kiện (Event):** Là tác nhân, sự việc xảy ra gây ra sự thay đổi từ trạng thái này sang trạng thái khác.
5. Siêu trạng thái xảy ra khi:
- Siêu trạng thái (superstate) được sử dụng khi cần nhóm các trạng thái con có liên quan lại với nhau.
- Có một sự kiện có thể xảy ra ở bất kỳ lúc nào trong nhiều trạng thái khác nhau.
6. Những hệ thống mà sơ đồ trạng thái sẽ hữu ích là những hệ thống có nhiều đối tượng, mỗi đối tượng trải qua nhiều trạng thái khác nhau trong suốt vòng đời của mình.

## Bài 11: Thiết kế

### Câu hỏi

1. Trong Design Class Diagram, một lớp gồm:
   
   - Tên lớp
   
   - Các thuộc tính đi kèm với kiểu dữ liệu cụ thể
   
   - Các phương thức với kiểu trả về
   
   - Quyền truy cập

2. 
   
- Lớp phân tích: nhấn mạnh các khái niệm nghiệp vụ, ít hoặc không có kiểu dữ liệu và quyền truy cập

- Lớp thiết kế: có thêm các chi tiết kỹ thuật (how): kiểu dữ liệu, visibility, exceptions, interfaces, ...
3. 
   
- Lớp điều khiển có nhiệm vụ thực hiện thao tác điều khiển, kết nối giữa các lớp biên và các lớp thực thể

- Lớp thực thể là các thực thể của hệ thống
4. Nên dùng lớp kết hợp khi  mà mối liên hệ giữa 2 thực thể cũng mang thông tin riêng.



## Bài 12: