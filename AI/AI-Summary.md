Kiến thức thi:
    Bài 1:
        BFS
        DFS
        Best-FS
        Leo đồi
        A*
        Nhánh cận
    Bài 2:
        MiniMax - Cắt tỉa Alpha, Beta. Cách làm:
            Sắp xếp loại hàng Min - Max so le, lấy hàng đầu tiên làm chuẩn
            Khởi đàu:
                a = âm vô cùng
                b = dương vô cùng
            Nếu đỉnh hiện tại(v) thuộc hàng Max:
                Mặc định:
                    v = âm vô cùng
                    a = kế thừa a từ đỉnh cha tại thời điểm đó
                    b = kế thừa b từ đỉnh cha tại thời điểm đó
                Kiểm tra từng đỉnh con(s):
                    Tìm trọng số đỉnh con nếu chưa có
                    v = Max(v, s)
                    Nếu v >= b thì kết thúc và cắt các nhánh con còn lại
                    Nếu ko:
                        a = Max(a, v)
                        Đưa v lên cha để xét
            Nếu đỉnh thuộc hàng Min:
                Mặc định:
                    v = dương vô cùng
                    a = kế thừa a từ đỉnh cha tại thời điểm đó
                    b = kế thừa b từ đỉnh cha tại thời điểm đó
                Kiểm tra từng đỉnh con(s):
                    Tìm trọng số đỉnh con nếu chưa có
                    v = Min(v, s)
                    Nếu v <= a thì kết thúc và cắt các nhánh con còn lại
                    Nếu ko:
                        b = Min(b, v)
                Đưa v lên cha để xét
    Bài 3:
        Logic mệnh đề
            a ^ b: a hội b
            a v b: a tuyển b
            -a: phủ định của a
            a => b: a kéo theo b
            a <=> b: a tương đương b
            
        Dạng chuẩn tắc tuyển:
            Là biểu thức chỉ có tuyển hoặc tuyển của các hội
            Ví dụ: (-a ^ b) v (c v -d)
            
        7 luật suy diễn
            Luật Modus Ponens:
                a => b, a suy ra b
            Luật Modus Tollens:
                a => b, -b suy ra -a
            Luật bắc cầu:
                a => b, b => c suy ra a => c
            Luật loại bỏ hội:
                a1 ^ a2 ^ ... ^ an suy ra ai
            Luật đưa vào hội:
                a1, a2, ..., an suy ra a1 ^ a2 ^ ... ^ an
            Luật đưa vào tuyển:
                ai suy ra a1 v a2 v ... v an
            Luật phân giải:
                a v b, -b v c suy ra a v c
        
        Phương pháp chứng minh diễn dịch:
            Đề bài yêu cầu chứng minh một công thức
            Dựa vào các công thức sẵn có và các Luật suy diễn
            Chứng minh các công thức khác để cuối cùng có thể chứng mình công thức cần chứng minh
        
        Câu tuyển:
            Là biểu thức chỉ có hội hoặc là hội của các tuyển
            Ví dụ: (a v b v -c) ^ d
        Luật tương đương:
            Các luật cơ bản:
                a => b = -a v b
                a <=> b = (a => b) ^ (b => a)
                -(-a) = a
            Luật De Morgan:
                -(a v b) = -a ^ -b
                -(a ^ b) = -a v -b
            Luật giao hoán:
                a V b = b v a
                a ^ b = b ^ a
            Luật kết hợp:
                (a v b) v c = a v (b v c)
                (a ^ b) ^ c = a ^ (b ^ c)
            Luật phân phối:
                a ^ (b v c) = (a ^ b) v (a ^ c)
                a v (b ^ c) = (a v b) ^ (a v c)
                
        Phương pháp chứng minh bác bỏ
            
            
        
