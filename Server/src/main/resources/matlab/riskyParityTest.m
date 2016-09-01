function []=riskyParityTest()
   windows={90;180;360};
   holds={30;60;90};
   N=2;
%    for N=2:6
%     for i=1:3
%        for j=1:3
        window=windows{1};
        hold=holds{1};
        name1=['riskyParityN',num2str(N),'Price.txt'];
        name2=['riskyParityN',num2str(N),'Fee.txt'];
        dataset=textread(name1);
        datafee=textread(name2);
%         display(dataset);
%         display(datafee);
        [w,rpturn]=riskyParity(dataset,datafee,N,window,hold);
        display(w);
        display(rpturn);
%         w1=['riskyParityN',num2str(N),'w',window,'H',hold,'w.txt'];
%         w2=['riskyParityN',num2str(N),'w',window,'H',hold,'rp.txt'];
%         fidw=fopen(w1,'wt');
%         fidr=fopen(w2,'wt');
%         [m,n]=size(w);
%         for i=1:1:m
%          for j=1:1:n
%             if j==n
%                 fprintf(fidw,'%g\n',w(i,j));
%             else
%                 fprintf(fidw,'%g\t',w(i,j));
%             end
%          end
%         end
%         [x,y]=size(rpturn);
%         for i=1:1:x
%             for j=1:1:y
%                if j==y
%                   fprintf(fidr,'%g\n',rpturn(i,j));
%                else
%                   fprintf(fidr,'%g\t',rpturn(i,j));
%                end
%             end
%         end
% %       end
% %     end
%       end
end