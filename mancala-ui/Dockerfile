FROM node:20 as build-stage

WORKDIR /home/app
COPY ./src ./src
COPY ./package.json .
COPY ./package-lock.json .
COPY ./angular.json .
COPY ./tsconfig.json .
COPY ./tsconfig.app.json .
COPY ./tsconfig.spec.json .
COPY ./tailwind.config.js .
RUN npm install
RUN npm run build -c production --output-path=./dist/mancala-ui --output-hashing=all

FROM nginx:stable-alpine
COPY ./nginx/nginx.conf /etc/nginx/nginx.conf
COPY --from=build-stage /home/app/dist/mancala-ui/browser /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]

