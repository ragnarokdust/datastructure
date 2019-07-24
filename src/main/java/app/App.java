package app;

import datastruct.LinkedListImpl;

// create : shift , push, insert
// read : get
// update : set
// delete : poll, pop, remove
public class App 
{
    public static void main( String[] args )
    {
        LinkedListImpl<String> list = new LinkedListImpl<String>();
        String[] names = {
            "공정환","김대현",
            "김기범","장재호"
        };

        // create
        for (String elem : names) {
            list.add(elem);
        }
        
        System.out.println("\n\nadd 초기화 \n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        
        // create first
        list.addFirst("이승철");
        System.out.println("\n\nshift : 이승철\n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }        

        // create last
        list.addLast("고질라");
        System.out.println("\n\npush : 고릴라\n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // create index
        list.add(1,"코끼리");
        System.out.println("\n\ninsert 1 : 코끼리\n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }        

        // update set
        list.set(0, "강사님");
        System.out.println("\n\nset : 이승철 -> 강사님\n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // delete pop
        String last = list.pop();
        System.out.println("\n\npop :" + last + "\n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // delete poll
        String first = list.poll();
        System.out.println("\n\npoll : " + first + "\n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // delete remove
        list.remove(2);
        System.out.println("\n\nremove : 2번 지우기 = 김대현 \n\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
