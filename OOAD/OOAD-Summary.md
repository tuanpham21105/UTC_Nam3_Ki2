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
    
    - State Machine Diagram: vòng đời của một đối tượng

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
   
   - Thể hiện **góc nhìn từ bên ngoài**của người dùng về chức năng mà hệ thống cần thực hiện
   
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
  
  -  Ví du: Khách hàng thuê xe đạp, lớp thuê chứa ngày bắt đầu thuê, ngày kết thúc,...

<img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-11-03-32-image.png" title="" alt="" data-align="center">

### Tiêu chuẩn của một lớp thiết kế tốt

- Các lớp nên phản ánh đúng đối tượng trong phạm vi bài toán

- Một lớp cần có cả dữ liệu và hành vi, tránh lớp chỉ toàn dữ liệu hoặc chỉ toàn hành vi

- Lớp phải có tínhcố kết cao, nên tập trung vào chỉ 1 trách nghiệm chính

### Câu hỏi

1. Sự khác nhau giữa đối tượng và lớp:
   
   - **Lớp:** Là một định nghĩa trừu tượng, đóng vai trò như một khuôn mẫu hoặc bản thiết kế cho các đối tượng có cùng đặc tính chung. Lớp giúp thực hiện trừu tượng hóa dữ liệu, bao gói và che giấu thông tin.
     
     - Ký hiệu: Thể hiện bằng hình chữ nhật chia làm 3 phần: Tên lớp (trên cùng), Các thuộc tính (giữa) và Các thao tác/phương thức (dưới cùng).
       
       <img src="file:///home/tuan-pham/snap/marktext/9/.config/marktext/images/2026-03-07-11-19-59-image.png" title="" alt="" data-align="center">
   
   - **Đối tượng:** Là một thực thể cụ thể (instance) được tạo ra từ một lớp. Thế giới thực bao gồm các đối tượng vật lý (xe tải, sinh viên), đối tượng khái niệm (quy trình) hoặc đối tượng phần mềm.
     
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