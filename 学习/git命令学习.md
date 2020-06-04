# 一、git创建远程仓库

## 1、从头开始创建

### 具体流程：

1. 创建文件夹，可以手动创建文件夹(右键新建文件夹)，也可以使用git命令行工具新建，对应的命令是

   $ mkdir filename   filename就是文件夹的名字，$ pwd  查看当前路径    $ touch filename  创建一个文件

2. 初始化仓库，使用命令 $ git init![image-20200603163952840](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603163952840.png)

   git init 初始化这个文件夹gitRepository作为仓库，且git是可以管理的

3. 将文件添加到版本库(仓库)

   1. 查看当前文件夹下有哪些文件  ll -ah![image-20200603164749321](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603164749321.png)

      当前文件夹下有两个文件，一个是.git文件，这个是git自动创建的，不可修改，另外一个是我们自己创建的，现在提交README.txt文件

   2. 使用$ git add命令告诉Git，把文件添加到仓库中 git add filename表示添加文件![image-20200603165253128](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603165253128.png)

   3. 使用$ git commit命令告诉Git，把文件提交到仓库中，其中-m后面的是提交文件时的说明信息，代表着改动记录，命令执行成功之后会有反馈，1 file changed:1个文件被改动 1 insertion(+)插入了1行内容![image-20200603165348468](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603165348468.png)

   4. 在github网站上创建一个远程仓库git@github.com:liangxiuhu/gitRepository.git![image-20200603170906455](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603170906455.png)

      

   5. 关联到远程库 $ git remote add origin git@github.com:liangxiuhu/gitRepository.git![image-20200603171456038](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603171456038.png)

   6. 获取远程库与本地同步合并(如果远程库不为空则必须做这一步，否则后面的提交会失败)，命令为：$ git pull --rebase origin master

   7. 把本地的内容推送到远程，命令：git push,新建的远程仓库是空的，所以使用的命令是$ git push -u origin master,等远程仓库里面有了内容之后，就可以直接使用$ git push origin master![image-20200603172129681](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603172129681.png)![image-20200603172151213](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200603172151213.png)

      

   ​	



## 2、提交修改的文件

1. git pull    ：拉取服务器文件，防止覆盖他人的提交信息
2. git status    : 查看当前项目中哪些文件被别人修改了，具体的状态如下：
   1. Untracked:未跟踪，一般为新增文件，此文件在文件夹中，但是并没有添加到git库，不参与版本控制。可通过git add 把状态改变为Staged
   2. Modified:文件已经被修改，仅仅只是修改，并没有进行其他操作
   3. deleted:文件已经删除，本地删除，服务器上还没有删除![image-20200604084621356](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200604084621356.png)
3. 将改变的文件提交至缓存
   1. git add .    将所有的修改的文件提交到缓存
   2. git add filename  将filename文件提交到缓存
   3. git add -u +路径    将目录下被修改过的被跟踪的文件提交到缓存
   4. git add -A +路径    将目录下被修改过的未被跟踪的文件提交到缓存![image-20200604084637150](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200604084637150.png)
4. 将代码提交到本地仓库     git commit -m '提交说明'![image-20200604084651375](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200604084651375.png)
5. 将缓存区代码提交到GIT服务器  git push![image-20200604084703223](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200604084703223.png)

