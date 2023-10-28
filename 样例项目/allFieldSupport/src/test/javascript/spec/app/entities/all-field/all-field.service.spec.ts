/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from '@/shared/date/filters';
import AllFieldService from '@/entities/all-field/all-field.service';
import { AllField } from '@/shared/model/all-field.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('AllField Service', () => {
    let service: AllFieldService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new AllFieldService();
      currentDate = new Date();
      elemDefault = new AllField(
        123,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        false,
        currentDate,
        currentDate,
        currentDate,
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            nameLocalDate: dayjs(currentDate).format(DATE_FORMAT),
            nameZonedDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameInstant: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a AllField', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            nameLocalDate: dayjs(currentDate).format(DATE_FORMAT),
            nameZonedDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameInstant: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            nameLocalDate: currentDate,
            nameZonedDateTime: currentDate,
            nameInstant: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a AllField', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a AllField', async () => {
        const returnedFromService = Object.assign(
          {
            allLowercaseRegex: 'BBBBBB',
            nameInteger: 1,
            nameLong: 1,
            nameBigDecimal: 1,
            nameFloat: 1,
            nameDouble: 1,
            nameBoolean: true,
            nameLocalDate: dayjs(currentDate).format(DATE_FORMAT),
            nameZonedDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameInstant: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameBlob: 'BBBBBB',
            nameAnyBlob: 'BBBBBB',
            nameImageBlob: 'BBBBBB',
            nameTextBlob: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            nameLocalDate: currentDate,
            nameZonedDateTime: currentDate,
            nameInstant: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a AllField', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a AllField', async () => {
        const patchObject = Object.assign(
          {
            allLowercaseRegex: 'BBBBBB',
            nameBigDecimal: 1,
            nameBoolean: true,
            nameLocalDate: dayjs(currentDate).format(DATE_FORMAT),
            nameZonedDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameBlob: 'BBBBBB',
            nameTextBlob: 'BBBBBB',
          },
          new AllField()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            nameLocalDate: currentDate,
            nameZonedDateTime: currentDate,
            nameInstant: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a AllField', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of AllField', async () => {
        const returnedFromService = Object.assign(
          {
            allLowercaseRegex: 'BBBBBB',
            nameInteger: 1,
            nameLong: 1,
            nameBigDecimal: 1,
            nameFloat: 1,
            nameDouble: 1,
            nameBoolean: true,
            nameLocalDate: dayjs(currentDate).format(DATE_FORMAT),
            nameZonedDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameInstant: dayjs(currentDate).format(DATE_TIME_FORMAT),
            nameBlob: 'BBBBBB',
            nameAnyBlob: 'BBBBBB',
            nameImageBlob: 'BBBBBB',
            nameTextBlob: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            nameLocalDate: currentDate,
            nameZonedDateTime: currentDate,
            nameInstant: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of AllField', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a AllField', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a AllField', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
