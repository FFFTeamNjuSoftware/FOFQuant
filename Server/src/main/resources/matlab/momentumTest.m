function[]=momentumTest()
   windows={90;180;360};
   holds={30;60;90};
   w='momentumSharpe.txt';
   fids=fopen(w,'wt');
   for N=2:6
       for i=1:3
           for j=1:3
            window=windows{i};
            hold=holds{j};
            name1=['riskyParityN',num2str(N),'Price.txt'];
            name2=['riskyParityN',num2str(N),'Fee.txt'];
            dataset=textread(name1);
            datafee=textread(name2);
            [w,rpturn]=momentum(dataset,datafee,N,window,hold);
            w1=['momentumN',num2str(N),'w',num2str(window),'H',num2str(hold),'w.txt'];
            w2=['momentumN',num2str(N),'w',num2str(window),'H',num2str(hold),'rp.txt'];
            fidw=fopen(w1,'wt');
            fidr=fopen(w2,'wt');
            [m,n]=size(w);
            for r=1:1:m
                for c=1:1:n
                    if (c==n)
                        fprintf(fidw,'%g\n',w(r,c));
                    else
                        fprintf(fidw,'%g\t',w(r,c));
                    end
                end
            end
            [x,y]=size(rpturn);
            for r=1:1:x
                for c=1:1:y
                    if c==y
                        fprintf(fidr,'%g\n',rpturn(r,c));
                    else
                        fprintf(fidr,'%g\t',rpturn(r,c));
                    end
                end
            end
            %             N£¬window£¬hold
            sharpe=calSharpe((rpturn')/100,N,0.037);
            fprintf(fids,'N=%g W=%g H=%g sharpe=%g\n',N,window,hold,sharpe);
            fclose(fidw);
            fclose(fidr);
           end      
       end
   end
   fclose(fids);
end