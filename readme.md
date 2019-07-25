# 목표

### # 1 LinkedList의 특징을 안다
### # 2 stack : push, pop 구현
### # 3 queue : shift, poll 구현

---

### # 1 LinkedList의 특징을 안다

- 장점

      ※ 삽입

- 단점
  
1. 항목 접근이 오래 걸린다. 

        - 첫 항목부터 순차적으로 접근하므로 최대 O(n)의 시간이 걸린다.
        - 양방향 접근시 O(n/2)의 시간이 걸린다.

2. 물리적으로 인접한 메모리에 위치해있지 않다. 

        배열의 항목은 물리적으로 인접해있어 접근 시간 단축과 캐싱에 유리하다고 한다. 하지만 연결리스트는 아니다.

3. 참조 포인터를 위한 메모리 공간이 낭비된다.

