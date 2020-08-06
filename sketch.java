////// sketch0.java

import java.awt.*;  import java.awt.event.*; 
public class sketch extends Frame {
 public sketch() {  
  setSize(150,200);
  addMouseListener(new myMouseHandler());
  addMouseMotionListener(new myMouseMotionHandler());
 }
int x0,y0,x,y;
public class myMouseHandler extends MouseAdapter {
public void mousePressed(MouseEvent e) { x0=e.getX(); y0=e.getY();  }
public void mouseReleased(MouseEvent e) { }
}
public class myMouseMotionHandler extends MouseMotionAdapter {
public void mouseMoved(MouseEvent e) {  }
public void mouseDragged(MouseEvent e){x=e.getX();y=e.getY();repaint();}
}
 public void paint(Graphics g) {  g.drawLine(x0,y0,x,y); }
 public static void main(String[] s){ new sketch().setVisible(true); }
}



//////////////////  sketch1.java

import java.awt.*;  import java.awt.event.*; import java.util.ArrayList;
class sketch extends Frame {
 sketch() {  
  setSize(150,200);
  addMouseListener(new myMouseHandler());
  addMouseMotionListener(new myMouseMotionHandler());
 }
int x0,y0,x,y;
ArrayList<myobj> mystuff = new ArrayList<myobj>();
class myobj {
  int a,b,c,d;
  myobj(int aa, int bb, int cc, int dd) { a=aa; b=bb; c=cc; d=dd; } }
class myMouseHandler extends MouseAdapter {
public void mousePressed(MouseEvent e) { x0=e.getX(); y0=e.getY();  }
public void mouseReleased(MouseEvent e){mystuff.add(new myobj(x0,y0,x,y));}
}
class myMouseMotionHandler extends MouseMotionAdapter {
public void mouseMoved(MouseEvent e) {  }
public void mouseDragged(MouseEvent e){x=e.getX();y=e.getY(); repaint();}
}
public void paint(Graphics g) { 
   g.drawLine(x0,y0,x,y);
   mystuff.forEach( l -> g.drawLine(l.a,l.b,l.c,l.d) );
 }
public static void main(String[] s){ new sketch().setVisible(true); }}

// "/cygdrive/c/Program Files/Java/jdk-11.0.1/bin/javac" sketch.java
// java sketch


///////////////////// sketchpad0.java:

import java.awt.*; import java.awt.event.*; import java.util.ArrayList;
class sketch extends Frame {
class obj { int xi,yi,xj,yj;
            obj(int aa, int bb, int cc, int dd) {
                 xi=aa; yi=bb; xj=cc; yj=dd; } }
ArrayList<obj> mysketch = new ArrayList<obj>();
int x0,y0;
 sketch() {  
  setSize(150,200);
  setLayout(new FlowLayout());  
  Button btn1 = new Button("back");
  add(btn1);
  btn1.addActionListener( e -> {
   if(mysketch.size()!= 0) mysketch.remove( mysketch.size()-1 ); repaint(); } );
  addMouseListener( new MouseAdapter() {
   public void mousePressed(MouseEvent e){ 
    x0 = e.getX(); 
    y0 = e.getY(); 
    mysketch.add( new obj(x0,y0,x0,y0) ); }} );
  addMouseMotionListener(new MouseMotionAdapter() {
   public void mouseDragged(MouseEvent e) {
    mysketch.remove( mysketch.size()-1 );
    mysketch.add(new obj(x0,y0,e.getX(),e.getY()));
    repaint(); }} );
 }
public void paint(Graphics g){mysketch.forEach(l->g.drawLine(l.xi,l.yi,l.xj,l.yj));}
public static void main(String[] args){ new sketch().setVisible(true); }
}

// "/cygdrive/c/Program Files/java/jdk1.8.0_121/bin/javac" sketch.java
// java sketch


/////////  Can consider this in MVC pattern:   aaa,bbb,ccc:

import java.awt.*; 
class aaa extends Frame {
public void paint(Graphics g){ mybbb.objs.forEach(l -> g.drawLine(l.xi,l.yi,l.xj,l.yj));}
public static void main(String[] args){ new aaa().setVisible(true); }
bbb mybbb = new bbb();
ccc myccc = new ccc(this,mybbb);
 aaa() {  
  setSize(150,200);
  setLayout(new FlowLayout());  
  Button btn1 = new Button("back");
  add(btn1);
  addMouseListener( myccc.new ccc1() );
  addMouseMotionListener(myccc.new ccc2());
  btn1.addActionListener(myccc.new ccc3());
 }
}

// "/cygdrive/c/Program Files/Java/jdk-11.0.1/bin/javac" aaa.java
// java aaa



import java.util.ArrayList;
class bbb {
 class int4 {
  int xi,yi,xj,yj;
  int4(int i1, int i2, int i3, int i4){
    xi=i1; yi=i2; xj=i3; yj=i4; }
  }
  ArrayList<int4> objs = new ArrayList<int4>();
}



import java.awt.event.*;
class ccc {
 aaa f;
 bbb m;
 ccc(aaa fr, bbb md) { f = fr; m=md; }

 int x0,y0;
 class ccc1 extends MouseAdapter {
  public void mousePressed(MouseEvent e){ 
   x0 = e.getX(); 
   y0 = e.getY(); 
   m.objs.add( m.new int4(x0,y0,x0,y0) ); 
 }}
 class ccc2 extends MouseMotionAdapter {
  public void mouseDragged(MouseEvent e) {
   m.objs.remove( m.objs.size()-1 );
   m.objs.add( m.new int4(x0,y0,e.getX(),e.getY()) );
   f.repaint();
 }}
 class ccc3 implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if(m.objs.size()!= 0) m.objs.remove( m.objs.size()-1 );
   f.repaint();
}}}







/////////////// sketchpad1

import java.awt.*;  import java.awt.event.*; import java.util.ArrayList;
class sketchpad extends Frame {
class obj {
 int xi,yi,xj,yj,md;
 obj(int aa, int bb, int cc, int dd, int mm) {
  xi=aa; yi=bb; xj=cc; yj=dd; md=mm; }
}
ArrayList<obj> mysketch = new ArrayList<obj>();
int x0,y0,mode=0;
 sketchpad() {  
  setSize(150,200);
  setLayout(new FlowLayout());  
  Button btn1 = new Button("back");
  Button btn2 = new Button("lines");
  Button btn3 = new Button("Rectangles");
  add(btn1); add(btn2); add(btn3);
  btn1.addActionListener( e -> {
   if(mysketch.size()!= 0) mysketch.remove( mysketch.size()-1 );
   repaint(); } );
  btn2.addActionListener( e -> mode=0 );
  btn3.addActionListener( e -> mode=1 );
  addMouseListener( new MouseAdapter() {
   public void mousePressed(MouseEvent e){ 
    x0 = e.getX(); 
    y0 = e.getY(); 
    mysketch.add( new obj(x0,y0,x0,y0,mode) ); }} );
  addMouseMotionListener(new MouseMotionAdapter() {
   public void mouseDragged(MouseEvent e) {
    mysketch.remove( mysketch.size()-1 );
    mysketch.add(new obj(x0,y0,e.getX(),e.getY(),mode));
    repaint(); }} );
 }
public void paint(Graphics g){ mysketch.forEach(l -> {
   if (l.md==0) g.drawLine(l.xi,l.yi,l.xj,l.yj);
           else g.drawRect(l.xi,l.yi,(l.xj-l.xi),(l.yj-l.yi));}); }
public static void main(String[] args){ new sketchpad().setVisible(true); }
}

// "/cygdrive/c/Program Files/Java/jdk-11.0.1/bin/javac" sketchpad.java
// java sketchpad








///// sketch4  (can find closest line)

import java.awt.*;  import java.awt.event.*; import java.util.ArrayList;
class sketch4 extends Frame {
class obj {
 int xi,yi,xj,yj,tp,sl=0;
 obj() { }
 obj(int a, int b, int c, int d, int t) {
  xi=a; yi=b; xj=c; yj=d; tp=t; }
 class ipair { 
  int x,y;
  ipair(int xx, int yy) { x=xx; y=yy; }
 }
 ipair  add(ipair U, ipair W) { return new ipair(U.x+W.x, U.y+W.y); }
 ipair  sub(ipair U, ipair W) { return new ipair(U.x-W.x, U.y-W.y); }
 ipair scale(ipair U, float s) { return new ipair((int)(s*(float)U.x), (int)(s*(float)U.y)); }
 int dist(ipair P, ipair Q) { return (int)Math.sqrt((P.x-Q.x)*(P.x-Q.x) + (P.y-Q.y)*(P.y-Q.y)); }
 int  dot(ipair P, ipair Q) { return P.x*Q.x + P.y*Q.y; }
 int segdist(int xp,int yp) { // distance from point (xp,yp) to line segment (xi,yi,xj,yj)
  ipair I=new ipair(xi,yi), J=new ipair(xj,yj), P=new ipair(xp,yp), V,N;
  V = sub(J,I);             // V is the vector from I to J
  int k = dot(V, sub(P,I)); // k is the non-normalized projection from P-I to V
  int L2= dot(V,V);         // L2 is the length of V, squared
  if (k<=0) N = I;          // if the projection is negative, I is nearest (N)
   else if (k>=L2) N = J;   // if the projection too large, J is nearest (N)
   else N = add(I, scale(V,(float)k/(float)L2)); // otherwise, N is scaled onto V by k/L2
   return dist(P,N);
 }
}
ArrayList<obj> objs = new ArrayList<obj>();
int x0,y0,type,select=0, dmin=9999999;
obj closest = new obj();
 sketch4() {  
  setSize(150,200);
  setLayout(new FlowLayout());  
  Button btn1 = new Button("back");
  Button btn2 = new Button("lines");
  Button btn3 = new Button("Rectangles");
  Button btn4 = new Button("Find Closest");
  add(btn1); add(btn2); add(btn3); add(btn4);
  btn1.addActionListener( e -> {
    if(objs.size()!= 0) objs.remove( objs.size()-1 );
    repaint(); } );
  btn2.addActionListener( e -> type=0 );
  btn3.addActionListener( e -> type=1 );
  btn4.addActionListener( e -> { select=1; closest.sl=0; repaint(); } );
  addMouseListener( new MouseAdapter() {
   public void mouseReleased(MouseEvent e){select=0;}
   public void mousePressed(MouseEvent e){ 
     x0 = e.getX(); 
     y0 = e.getY(); 
     objs.add( new obj(x0,y0,x0,y0,type) ); }} );
  addMouseMotionListener(new MouseMotionAdapter() {
    public void mouseMoved(MouseEvent e) {
      if (select==1 && objs.size()!=0) {
          objs.forEach( ob -> {
             ob.sl = 0;
             int d=ob.segdist(e.getX(),e.getY());
             if( dmin > d ) { closest=ob; dmin=d; }
             } );
      closest.sl=1;
      repaint();
    }   }
    public void mouseDragged(MouseEvent e) {
      objs.remove( objs.size()-1 );
      objs.add(new obj(x0,y0,e.getX(),e.getY(),type));
      repaint(); }} );
 }
int max(int a,int b){return a>b ? a:b;}
int min(int a,int b){return a>b ? b:a;}
int abs(int a){return a>0 ? a : -a;}
public void paint(Graphics g){ 
   dmin=9999999;
   objs.forEach( ob -> {
   if (ob.sl==1) g.setColor(Color.RED);
   if (ob.tp==0) g.drawLine(ob.xi,ob.yi,ob.xj,ob.yj);
     else g.drawRect(min(ob.xi,ob.xj),min(ob.yi,ob.yj),
                     abs(ob.xi-ob.xj),abs(ob.yi-ob.yj));
   if (ob.sl==1) g.setColor(Color.BLACK); } ); }
public static void main(String[] args){ new sketch4().setVisible(true); }  }

// "/cygdrive/c/Program Files/Java/jdk-11.0.1/bin/javac" sketch4.java
// java sketch4
